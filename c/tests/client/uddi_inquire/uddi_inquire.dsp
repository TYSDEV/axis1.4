# Microsoft Developer Studio Project File - Name="uddi_inquire" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=uddi_inquire - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "uddi_inquire.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "uddi_inquire.mak" CFG="uddi_inquire - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "uddi_inquire - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "uddi_inquire - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "uddi_inquire - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W3 /GX /O2 /I "..\..\..\include" /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x409 /d "NDEBUG"
# ADD RSC /l 0x409 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 AxisClient.lib /nologo /subsystem:console /machine:I386 /out:"../../../../bin/uddi_inquire.exe" /libpath:"../../../bin"

!ELSEIF  "$(CFG)" == "uddi_inquire - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /I "..\..\..\include" /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD BASE RSC /l 0x409 /d "_DEBUG"
# ADD RSC /l 0x409 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 AxisClient_D.lib /nologo /subsystem:console /debug /machine:I386 /out:"../../../../bin/uddi_inquire_D.exe" /pdbtype:sept /libpath:"../../../bin"

!ENDIF 

# Begin Target

# Name "uddi_inquire - Win32 Release"
# Name "uddi_inquire - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\gen_src\accessPoint.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\add_publisherAssertions.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\address.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\addressLine.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\assertionStatusItem.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\assertionStatusReport.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\authToken.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingKey.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingTemplate.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingTemplates.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessDetailExt.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntity.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntityExt.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessInfos.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessKey.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessList.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessService.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessServices.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\categoryBag.cpp
# End Source File
# Begin Source File

SOURCE=.\Client.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\contact.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\contacts.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_binding.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_business.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_publisherAssertions.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_service.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_tModel.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\description.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\direction.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discard_authToken.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discoveryURL.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discoveryURLs.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\dispositionReport.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\email.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\errInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_binding.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_business.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_relatedBusinesses.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_service.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_tModel.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\findQualifiers.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_assertionStatusReport.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_authToken.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_bindingDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_businessDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_businessDetailExt.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_publisherAssertions.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_registeredInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_serviceDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_tModelDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\hostingRedirector.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\identifierBag.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\Inquire.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\InquireService_AxisClientException.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\instanceDetails.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keyedReference.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keysOwned.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keyType.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\name.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\overviewDoc.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\phone.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\publisherAssertion.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\publisherAssertions.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\registeredInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessesList.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessInfos.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\result.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_binding.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_business.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_service.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_tModel.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceInfos.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceKey.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceList.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\set_publisherAssertions.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\sharedRelationships.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModel.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelBag.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelDetail.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInfos.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInstanceDetails.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInstanceInfo.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelKey.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelList.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\truncated.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\URLType.cpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\validate_values.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\gen_src\accessPoint.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\add_publisherAssertions.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\address.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\address_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\addressLine.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\addressLine_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\assertionStatusItem.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\assertionStatusItem_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\assertionStatusReport.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\authToken.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingKey.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingKey_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingTemplate.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingTemplate_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\bindingTemplates.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessDetailExt.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntity.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntity_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntityExt.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessEntityExt_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessInfo_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessInfos.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessKey.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessKey_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessList.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessService.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessService_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\businessServices.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\categoryBag.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\contact.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\contact_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\contacts.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_binding.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_business.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_publisherAssertions.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_service.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\delete_tModel.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\description.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\description_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\direction.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discard_authToken.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discoveryURL.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discoveryURL_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\discoveryURLs.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\dispositionReport.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\email.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\email_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\errInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_binding.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_business.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_relatedBusinesses.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_service.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\find_tModel.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\findQualifier_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\findQualifiers.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_assertionStatusReport.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_authToken.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_bindingDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_businessDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_businessDetailExt.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_publisherAssertions.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_registeredInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_serviceDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\get_tModelDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\hostingRedirector.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\identifierBag.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\Inquire.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\InquireService_AxisClientException.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\instanceDetails.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keyedReference.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keyedReference_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keysOwned.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\keyType.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\name.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\name_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\overviewDoc.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\phone.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\phone_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\publisherAssertion.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\publisherAssertion_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\publisherAssertions.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\registeredInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessesList.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessInfo_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\relatedBusinessInfos.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\result.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\result_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_binding.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_business.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_service.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\save_tModel.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceInfo_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceInfos.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceKey.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceKey_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\serviceList.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\set_publisherAssertions.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\sharedRelationships.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\sharedRelationships_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModel.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModel_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelBag.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelDetail.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInfo_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInfos.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInstanceDetails.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInstanceInfo.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelInstanceInfo_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelKey.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelKey_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\tModelList.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\truncated.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\uploadRegister_Array.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\URLType.hpp
# End Source File
# Begin Source File

SOURCE=.\gen_src\validate_values.hpp
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# End Group
# Begin Source File

SOURCE=..\gen_src\Makefile.am
# End Source File
# Begin Source File

SOURCE=.\gen_src\Makefile.am
# End Source File
# End Target
# End Project
