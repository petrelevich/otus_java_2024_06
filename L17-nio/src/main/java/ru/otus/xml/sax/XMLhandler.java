package ru.otus.xml.sax;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLhandler extends DefaultHandler {
    private static final Logger logger = LoggerFactory.getLogger(XMLhandler.class);
    private final List<Share> result = new ArrayList<>();
    private Share shareData;

    private static final String SHARES = "shares";
    private static final String SHARE = "share";
    private static final String TICKER = "ticker";
    private static final String LAST = "last";
    private static final String DATE = "date";

    private boolean sharesFlag = false;
    private boolean shareFlag = false;
    private boolean tickerFlag = false;
    private boolean lastFlag = false;
    private boolean dateFlag = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        logger.info("Start Element:{}", qName);

        if (SHARES.equals(qName)) {
            sharesFlag = true;
            return;
        }

        if (sharesFlag && (SHARE.equals(qName))) {
            shareFlag = true;
            shareData = new Share();
            return;
        }

        if (shareFlag) {
            switch (qName) {
                case TICKER -> tickerFlag = true;
                case LAST -> lastFlag = true;
                case DATE -> dateFlag = true;
                default -> throw new IllegalArgumentException("unknown name:" + qName);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        logger.info("End Element:{}", qName);

        switch (qName) {
            case SHARES -> sharesFlag = false;
            case SHARE -> {
                shareFlag = false;
                result.add(shareData);
                shareData = null;
            }
            case TICKER -> tickerFlag = false;
            case LAST -> lastFlag = false;
            case DATE -> dateFlag = false;
            default -> throw new IllegalArgumentException("unknown name:" + qName);
        }
    }

    @Override
    @SuppressWarnings("java:S1197")
    public void characters(char ch[], int start, int length) {
        var value = new String(ch, start, length);

        if (tickerFlag) {
            logger.info("ticker:{}", value);
            shareData.setTicker(value);
        }

        if (lastFlag) {
            logger.info("last:{}", value);
            shareData.setLast(Double.parseDouble(value));
        }

        if (dateFlag) {
            logger.info("date:{}", value);
            shareData.setDate(value);
        }
    }

    public List<Share> getResult() {
        return result;
    }
}
