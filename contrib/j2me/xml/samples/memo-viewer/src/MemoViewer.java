import java.io.InputStream;
import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.TextBox;

import javax.microedition.midlet.MIDlet;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection; 

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * A memo viewer using J2ME JAXP
 *  
 * @author Ias (iasandcb@tmax.co.kr)
 */

public class MemoViewer extends MIDlet implements CommandListener {

    private static final String MEMO_URL = "http://www.iasandcb.pe.kr/memo.xml";
    private static final Command CMD_EXIT =
    new Command("Exit", Command.EXIT, 1);
    private static final Command CMD_VIEW =
    new Command("View", Command.ITEM, 1);

    private Display display;
    private SAXParserFactory factory;
    private SAXParser parser;
    private MemoHandler memoHandler;
    private TextBox memoBox;
    
    public MemoViewer() {
        memoBox = new TextBox("Memo Viewer", "Disconnected", 100, TextField.ANY);
        factory = SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        }
        memoHandler = new MemoHandler(memoBox); 
    }

    protected void startApp() {
        display = Display.getDisplay(this);
        memoBox.addCommand(CMD_EXIT);
        memoBox.addCommand(CMD_VIEW);
        memoBox.setCommandListener(this);
        display.setCurrent(memoBox);        
    }

    public void commandAction(Command c, Displayable d) {

        if (c == CMD_EXIT) {
            destroyApp(false);
            notifyDestroyed();
        }
        else if (c == CMD_VIEW) {
            StreamConnection con = null;
            InputStream in = null;
            try {
                con = (StreamConnection) Connector.open(MEMO_URL);
                in = con.openInputStream();
                try {
                    parser.parse(in, memoHandler);
                }
                catch (SAXParseException se) {
                }
                catch (SAXException se) {
                }
            } catch (IOException e) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    protected void destroyApp(boolean unconditional) {
    }

    protected void pauseApp() {
    }
    
    private class MemoHandler extends DefaultHandler {
        private StringBuffer textBuffer;
        private TextBox memoBox;

        MemoHandler(TextBox memoBox) {
            this.memoBox = memoBox;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (localName.equals("subject")) {
                textBuffer = null;
            } else if (localName.equals("content")) {
                textBuffer = null;
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (localName.equals("subject")) {
                memoBox.setTitle(textBuffer.toString());
            } else if (localName.equals("content")) {
                memoBox.setString(textBuffer.toString());
            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            String s = new String(ch, start, length);
            if (textBuffer == null) {
                textBuffer = new StringBuffer(s);
            }
            else {
                textBuffer.append(s);
            }
        }
    }
}

