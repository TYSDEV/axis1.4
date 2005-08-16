/*
 * Created on Jan 28, 2005
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis2.transport.mail.server;

public class MailSrvConstants {
    public final static String FROM_ADDRESS = "transport.mail.from";

    public final static String TO_ADDRESS = "transport.mail.to";

    public final static String SUBJECT = "transport.mail.subject";


    public final static String POP3_HOST = "transport.mail.pop3.host";
    public final static String POP3_USER = "transport.mail.pop3.user";
    public final static String POP3_PASSWORD = "transport.mail.pop3.password";
    public final static String POP3_PORT = "transport.mail.pop3.port";

    public final static String RAPLY_TO = "transport.mail.replyToAddress";
    public final static String HEADER_SOAP_ACTION = "transport.mail.soapaction";

    public final static String CONTENT_TYPE = "transport.mail.contenttype";

    public final static String CONTENT_LOCAION = "transport.mail.contentlocation";


    public final static String SMTP_HOST = "transport.mail.smtp.host";
    public final static String SMTP_USER = "transport.mail.smtp.user";
    public final static String SMTP_PASSWORD = "transport.mail.smtp.password";
    public final static String SMTP_PORT = "transport.mail.smtp.port";
    
    public final static String DEFAULT_CHAR_SET = "us-ascii";
    public final static String DEFAULT_CHAR_SET_ENCODING = "7bit";
    public final static String DEFAULT_CONTENT_TYPE = "text/xml";

    public final static int SMTP_SERVER_PORT = (1024 + 25);
    public final static String SERVER_DOMAIN = "localhost";
    public final static int POP_SERVER_PORT = (1024 + 110);

    public final static String COMMAND_UNKNOWN = "550 Unknown command";
    public final static String COMMAND_EXIT = "EXIT";
    public final static String MAIL_OK = "250 OK performed command MAIL";
    public final static String MAIL_ERROR = "550 Error processign MAIL command";
    public final static String RCPT_OK = "250 OK performed command RCPT";
    public final static String RCPT_ERROR = "550 Unknown recipient";
    public final static String DATA_START_SUCCESS = "354 OK Ready for data";
    public final static String DATA_END_SUCCESS = "250 OK finished adding data";
    public final static String COMMAND_TRANSMISSION_END = "221 Closing SMTP service.";
    public final static String HELO_REPLY = "250 OK";
    public final static String OK = "+OK ";
    public final static String ERR = "-ERR ";
    public final static String USER = "USER";
    public final static String PASS = "PASS";
    public final static String STAT = "STAT";
    public final static String LIST = "LIST";
    public final static String RETR = "RETR";
    public final static String DELE = "DELE";
    public final static String NOOP = "NOOP";
    public final static String RSET = "RSET";
    public final static String QUIT = "QUIT";
}
