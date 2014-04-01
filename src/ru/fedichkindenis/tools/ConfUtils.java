package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import ru.fedichkindenis.servlets.GetListGames;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 13.10.13
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ConfUtils {

    private static final Logger log = Logger.getLogger(ConfUtils.class);

    public static String getParamConfigXML(InputStream file, String param){

        String result = null;
        String tmp = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(file);

            while (reader.hasNext() && result == null){
                int event = reader.next();

                switch (event){
                    case XMLStreamConstants.CHARACTERS:
                        tmp = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if(param.equalsIgnoreCase(reader.getLocalName())){
                            result = tmp;
                        }
                }
            }

        } catch (XMLStreamException e) {
            log.error(e.getMessage());
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return result;
    }
}
