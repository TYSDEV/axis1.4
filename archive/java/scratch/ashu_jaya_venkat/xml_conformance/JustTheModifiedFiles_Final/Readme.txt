*************
When moved to main area, these contents (except this readme.txt file) should go into the folder modules/xml
*************

Under this folder are a list of files newly added as part of infoset support work and a few existing files changed accordingly. A transcript of changes made to what currently exists in the main area follows.

Newly added interfaces:
-----------------------
org/apache/axis/om/OMPI.java		---
org/apache/axis/om/OMComment.java		|-> These interfaces are aded to incorporate new functionality
org/apache/axis/om/OMDTD.java		---


Newly added classes:
--------------------
(Implementation classes for above added interfaces)

org/apache/axis/om/impl/llom/OMPIImpl.java
org/apache/axis/om/impl/llom/OMCommentImpl.java
org/apache/axis/om/impl/llom/OMDTDImpl.java


Modified existing interfaces:
-----------------------------
org/apache/axis/om/OMFactory.java 	-- added a few methods signatures to create PI, DTD and Comment nodes


Modified existing classes: (This one is the main area for review)
--------------------------
org/apache/axis/om/impl/llom/OMDocument.java 	-- it's made to extend OMElement, to have the child API functionality for it etc.

org/apache/axis/om/impl/llom/OMElementImpl.java	-- constructor code is changed where a default namespace (prefix=xml and uri="http://www.w3.org/XML/1998/namespace" is declared for every element created)

org/apache/axis/om/impl/llom/builder/StAXOMBuilder.java	-- capturing of DTD, PI, Comment events and hooking them to the corresponding implementation)

org/apache/axis/om/impl/llom/factory/OMLinkedListImplFactory.java (added a few method signatures to reflect PI, Comment and DTD implementations I did)

Build related changes:
----------------------
The test case I added requires xmlunit-1.0.jar as a dependency. For this reason the 
modules/xml/project.xml is slightly modified to add an extra dependency and is also provided in this folder.



NOTE:
*****
I've tested on a clean copy of code gotten from latest SVN (6th June, 11A.M) the above changes and got a maven 'build successful'.
The test case provided under the test expects a folder XMLSuite to be present under modules/xml/test-resources directory. 
