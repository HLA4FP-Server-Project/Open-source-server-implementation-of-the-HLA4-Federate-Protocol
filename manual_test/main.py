from pathlib import Path
import shutil
import sys
import os
import subprocess
from dataclasses import dataclass
from threading import Thread
import time

# Should be a commit
FED_PRO_CLIENT_VERSION = "6f85a33e9b02ce3b2ad6ea849cc90427b78f0fab"
FED_PRO_REPO = "git@github.com:Pitch-Technologies/FedProClient.git"

def main():
    run_open_source_server_implementation_of_the_hla4_federate_protocol()
    chat_jar = get_built_chat_sample_jar()

    chat_a = start_fed_pro(chat_jar)
    chat_a.send("user1abc\n") # Log in
    chat_a.assert_output_contains("Type messages you want to send.") # Message which should be printed when logged in successfully
    chat_a.clear_output()

    chat_b = start_fed_pro(chat_jar)
    chat_b.send("user2abc\n") # Log in
    chat_b.assert_output_contains("Type messages you want to send.") 
    chat_b.clear_output()

    chat_a.assert_output_contains("user2abc has joined")

    chat_a.send("123abc456\n")
    chat_b.assert_output_contains("user1abc: 123abc456")

    print("TEST SUCCESS")

    pass


def start_fed_pro(jar: Path) -> "FedProSample":
    sample = FedProSample(jar)
    sample.wait_untill("[localhost]")
    print("Waiting for client to connect")
    sample.send("\n") # to make it connect
    sample.wait_untill("Enter your name")
    print("Client connected")
    sample.clear_output()
    return sample

class FedProSample:
    process: subprocess.Popen[str]
    buffer: str
    def __init__(self, jar: Path) -> None:
        self.buffer = ""
        self.process = subprocess.Popen(["java", "-jar", jar.name], cwd=jar.parent, encoding="utf-8", stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
        read_thread = Thread(target=self.__read_loop)
        read_thread.daemon = True
        read_thread.start()
        pass
    def send(self, str: str):
        self.process.stdin.write(str)
        self.process.stdin.flush()
        time.sleep(2) # Give some time to respond
    def __read_loop(self):
        while True:
            try:
                self.buffer += self.process.stdout.read(1)
            except IOError:
                pass
    def clear_output(self):
        self.buffer = ""
    def assert_output_contains(self, txt: str):
        assert txt in self.buffer
    def assert_output_does_not_contain(self, txt: str):
        assert not txt in self.buffer
    def wait_untill(self, txt: str):
        while True:
            if txt in self.buffer:
                break
            time.sleep(0.05)

def run_open_source_server_implementation_of_the_hla4_federate_protocol() -> subprocess.Popen[bytes]:
    process = subprocess.Popen(["./mvnw", "-B", "compile", "exec:exec"], cwd=repo_root(), stdout=subprocess.DEVNULL)
    time.sleep(5)
    return process

def get_built_chat_sample_jar():
    repo_dirty = sync_fed_pro_repo()
    jar_file = fedpro_root() / "java/dist/samples/chat-java-hla4-fedpro/chat-java-hla4-fedpro.jar"
    if repo_dirty or not jar_file.exists():
        build_fed_pro()
    if not jar_file.exists():
        print(f"Something went wrong during build. Expected {jar_file} to be created now")
        sys.exit(1)
    return jar_file

def build_fed_pro():
    check_tool("ant")
    subprocess.run(["ant", f"-Djdk.home.11={os.environ["JAVA_HOME"]}", "-noinput", "-buildfile", "java/build.xml"], cwd=fedpro_root()).check_returncode()

def sync_fed_pro_repo() -> bool:
    dirty = False
    check_tool("git")
    repo_location = fedpro_root()
    # Ensure repo exists
    if not repo_location.exists():
        repo_location.mkdir()
        subprocess.run(["git", "init"], cwd=repo_location).check_returncode()
    os.chdir(repo_location)
    # Ensure HEAD is on the right commit
    current_commit = subprocess.run(["git", "rev-parse", "HEAD"], capture_output=True, encoding="utf-8")
    if current_commit.returncode != 0 or current_commit.stdout.strip() != FED_PRO_CLIENT_VERSION:
        dirty = True
        # Fetch from remote if this commit is unknown to us
        if subprocess.run(["git", "cat-file", "-t", FED_PRO_CLIENT_VERSION]).returncode != 0:
            print(f"Fetching FedProClient from remote")
            subprocess.run(["git", "fetch", "--depth", "1", FED_PRO_REPO, FED_PRO_CLIENT_VERSION]).check_returncode()
        # Checkout the right commit
        subprocess.run(["git", "checkout", "--detach", FED_PRO_CLIENT_VERSION]).check_returncode()
    return dirty

def check_tool(tool: str):
    if shutil.which(tool) is None:
        print(f"Can't find `{tool}` on path. Please make sure it's available")
        print("Aborting")
        sys.exit(1)

def fedpro_root() -> Path:
    return test_root() / "FedProClient"

def repo_root() -> Path:
    return test_root().parent

def test_root() -> Path:
    """The folder named `manual_test`"""
    return Path(__file__).parent.resolve()

if __name__ == "__main__":
    main()