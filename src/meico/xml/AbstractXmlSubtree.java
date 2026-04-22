package meico.xml;

import nu.xom.Element;
import nu.xom.canonical.Canonicalizer;

import java.io.IOException;

/**
 * This is the prototype for classes that occur within the Mpm data structure.
 * @author Axel Berndt
 */

public abstract class AbstractXmlSubtree {
    private Element xml = null;   // the actual xml data

    /**
     * a getter for the XOM Element/xml representation
     * @return
     */
    public Element getXml() {
        return this.xml;
    }

    /**
     * this sets the xml data
     * not for the public, hence, protected
     * @param xml
     */
    protected void setXml(Element xml) {
        this.xml = xml;
    }

    /**
     * set the data of this object, this parses the xml element and generates the according data structure
     * @param xml
     * @throws Exception
     */
    protected abstract void parseData(Element xml) throws Exception;

    /**
     * @return String with the XML code
     */
    public synchronized String toXml() {
        if (this.xml == null)
            return "";
        return this.xml.toXML();
    }

    /**
     * This method compares two XML subtrees via string comparison. The XMLs are canonicalized before comparison.
     * Hence, this is a rather expensive operation! Also, keep in mind, if IDs differ or the sequence of elements
     * differs, the XMLs are not equal.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractXmlSubtree))
            return false;

        AbstractXmlSubtree other = (AbstractXmlSubtree)obj;

        // compare
        return AbstractXmlSubtree.equals(this.getXml(), other.getXml());
    }

    /**
     * This method compares two XML subtrees via string comparison. The XMLs are canonicalized before comparison.
     * Hence, this is a rather expensive operation! Also, keep in mind, if IDs differ or the sequence of elements
     * differs, the XMLs are not equal.
     * @param a
     * @param b
     * @return
     */
    protected static boolean equals(Element a, Element b) {
        // an output stream for the canonicalizer that makes both XMLs comparable
        AbstractXmlSubtree.OutputStream thisOutput = new OutputStream();
        AbstractXmlSubtree.OutputStream otherOutput = new OutputStream();

        // the canonicalizer that makes both XML strings comparable
        Canonicalizer canonicalizer = new Canonicalizer(thisOutput, Canonicalizer.EXCLUSIVE_XML_CANONICALIZATION);

        // canonicalize this's XML
        try {
            canonicalizer.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String thisXml = thisOutput.toString();
//        System.out.println(thisXml);

        // canonicalize the other XML
        canonicalizer = new Canonicalizer(otherOutput, Canonicalizer.EXCLUSIVE_XML_CANONICALIZATION);
        try {
            canonicalizer.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String otherXml = otherOutput.toString();
//        System.out.println(otherXml);

        // compare
        return thisXml.equals(otherXml);
    }

    /**
     * a helper class for XML tree comparison and canonicalization
     */
    private static class OutputStream extends java.io.OutputStream {
        private final StringBuilder string = new StringBuilder();

        @Override
        public void write(int b) throws IOException {
            this.string.append((char) b );
        }

        public String toString() {
            return this.string.toString();
        }
    }
}
