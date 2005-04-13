Dt. 23 March 2005

The following implementation provides a SAAJ wrapper on the OM implementation of SOAP. SAAJ
specific interfaces are present in javax.xml.soap package and have been taken from Axis 1.2
implementation. The implementation of these interfaces using the OM specific classes (thereby
providing a SAAJ wrapper on OM) is in org.apache.axis.saaj package. 

The implementation is not complete yet and supports only construction of a basic soap message
and the operations related to it. The implementation is linked to OM implementation and likely
to change when the OM implementation is changed.

A test client is provided to demonstrate creating a basic soap message and sending it to the server.

Ashutosh Shahi