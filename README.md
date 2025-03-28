# Open-source-server-implementation-of-the-HLA4-Federate-Protocol

High Level Architecture (HLA) is an IEEE standard for distributed interactive simulation. It's been adopted by many organizations to stitch various components together for international exercises. Every participant in an HLA federation needs to be able to communicate with a Run Time Infrastructure (RTI). The RTI generally comes from proprietary vendors, and clients need proprietary C++ and Java libraries to interact with it. In other words: the Modeling & Simulation community suffers from heavy vendor lock-in.

The new HLA version (4) includes a Federate Protocol, which defines a universal protobuf-based data exchange that removes the need for vendor-dependent proprietary libraries and SDK's for clients. What is currently missing is a server implementation of HLA4FP, which translates calls from a HLA4FP client to an RTI.

An open-source, re-usable HLA4FP server is of critical value to the M&S community. It's an ambitious project but also an opportunity to make real impact.

# GOALS
- A developer-friendly, working HLA4FP server in Java
- The implementation must be robust; it should have extensive automated testing and high code quality
- The server should be able to support RTI's from multiple vendors (Pitch; MAK; Portico)
- The server should be designed in such a way that makes implementation in other programming languages relatively easy (e.g., through a well-written design document)
- If time allows, design documents on how the server could be extended to support the WebLVC protocol

# SPECIFICATIONS
- Paper from the RTI vendor Pitch about HLA4FP https://pitchtechnologies.com/wp-content/uploads/2020/06/18W-SIW-037-1.pdf
- General paper about HLA4FP https://www.researchgate.net/publication/358594204_HLA_4_Federate_Protocol_-_Requirements_and_Solutions
- HLA4FP presentation slides from Pitch https://www.researchgate.net/publication/362620670_HLA_4_at_SISO_Symposium_on_MS_Standards_at_IT2EC_2022
- SISO WebLVC standard https://www.sisostandards.org/resource/resmgr/standards_products/siso-std-017-2022_weblvc_pro.pdf
- HLA Evolved (previous version) available at https://standards.ieee.org/ieee/1516/3744/
- Demo client implementations for HLA4FP https://github.com/Pitch-Technologies
- Introduction to HLA 4 conference tutorial https://www.xcdsystem.com/iitsec/proceedings/index.cfm?Year=2024&AbID=134366&CID=1060
- We will provide additional documentation and example code where necessary

- 
