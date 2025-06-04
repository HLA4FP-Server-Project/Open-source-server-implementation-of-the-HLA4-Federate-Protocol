from pathlib import Path
import shutil
import sys
import os
import subprocess

# Should be a commit
FED_PRO_CLIENT_VERSION = "6f85a33e9b02ce3b2ad6ea849cc90427b78f0fab"
FED_PRO_REPO = "git@github.com:Pitch-Technologies/FedProClient.git"

def main():
    pass

def get_built_chat_sample_jar():
    repo_dirty = sync_fed_pro_repo()
    jar_file = fedpro_root() / "FedProClient/java/dist/samples/chat-java-hla4-fedpro/chat-java-hla4-fedpro.jar"
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
    os.chdir(repo_location)
    # Ensure repo exists
    if not repo_location.exists():
        repo_location.mkdir()
        subprocess.run(["git", "init"]).check_returncode()
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