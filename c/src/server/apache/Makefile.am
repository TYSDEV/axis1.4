lib_LTLIBRARIES = libaxiscpp_mod.la
AM_CPPFLAGS = -Wshadow -Wall -pedantic -ansi
libaxiscpp_mod_la_SOURCES = mod_axis.c

libaxiscpp_mod_la_LIBADD   = ../../common/libcommon.la ../../engine/libengine.la ../../soap/libsoap.la ../../wsdd/libwsdd.la ../../xml/libxml.la -L$(AXISCPP_HOME)/lib/xerces-c -lxerces-c -ldl -lstdc++


INCLUDES = -I$(AXISCPP_HOME)/include
