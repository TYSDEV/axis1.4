
SAAJ for OM
============

The objective is to provide a SAAJ implementation layer over the OM. Source for SAAJ interfaces are taken from Axis 1.2. The implementation of these interfaces using the OM specific classes is in org.apache.axis.saaj package. 

The implementation is not complete yet and supports only construction of a basic soap message and the operations related to it. The implementation is linked to OM implementation and likely to change when the OM implementation is changed.

A test client is provided to demonstrate creating a basic soap message and sending it to the server.


