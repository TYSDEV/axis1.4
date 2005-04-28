This is a readme file explaining the contents of this folder.

The folder XMLConformanceTestingProject contains an eclipse project that is used to do the testing. It can be readily loaded as a java project in IDE to quickly rerun the test. (Jar files location might have to be changed).
And coming to the OM code in there. I have tweaked OM quite a bit to add OMPI and OMComment functionality and also some minimalistic functionality for DTD support. Actually DTD support that exists in it is just a stupid implementation which treats a DTD element as a normal text element, captures it as a text of characters as is only to vomit it out at the time of serialization. Yeah! it's an utterly naive implementation but then I did so atleast to get away with some basic blockages that DTD elements are causing during parsing itself.

As part of this tweaking some files have been modified/added, here follows a list of them

Interfaces modified:
--------------------
org/apache/axis/om/OMFactory.java (added a few methods signatures)

Interfaces added:
-----------------
org/apache/axis/om/OMPI.java		(extra implementation)
org/apache/axis/om/OMComment.java	(	do	)
org/apache/axis/om/OMDTD.java		(	do	)

Classes modified:
-----------------
org/apache/axis/om/impl/llom/OMDocument.java (it's made to extend OMElement, to have the child API functionality for it etc.)
org/apache/axis/om/impl/llom/OMElementImpl.java (constructor code is changed where a default namespace is declared for every element created)
org/apache/axis/om/impl/llom/builder/StAXOMBuilder.java (capturing of DTD, PI, Comment events and hooking them to the corresponding implementation)
org/apache/axis/om/impl/llom/factory/OMLinkedListImplFactory.java (added a few method signatures to reflect PI, Comment and DTD implementations I did)

Classes added:
--------------
org/apache/axis/om/impl/llom/OMPIImpl.java
org/apache/axis/om/impl/llom/OMCommentImpl.java
org/apache/axis/om/impl/llom/OMDTDImpl.java


Apart from these the testing code is under the 'junittesting' package inside the project. The file XMLConformanceTest.java has the latest code I used to test the parsing and serialization stuff. The content of wiki is pretty outdated and this is the final one.

** The XMLSuite.jar provided here is a zip of a directory that contains only the valid version 1.0 compliant XML files
** For those who want to rerun the tests and see the results, please edit the location of the XMLSuite directory (line 48 of junittesting.XMLConformanecTest) to the location where you would unzip this XMLSuite.zip given here.
