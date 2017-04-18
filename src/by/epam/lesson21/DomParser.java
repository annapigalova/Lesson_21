package by.epam.lesson21;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class DomParser {

	public static void main(String[] args) throws SAXException, IOException {

		DOMParser parser = new DOMParser();
		parser.parse("RentalShopItems.xml");
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		
		List<Item> itemList = new ArrayList<Item>();
		NodeList itemNodes = root.getElementsByTagName("item");
		
		Item item = null;

		for (int i = 0; i < itemNodes.getLength(); i++) {
			
			item = new Item();
			Element itemElement = (Element) itemNodes.item(i);

			item.setId(Integer.parseInt(getSingleChild(itemElement, "id").getTextContent().trim()));
			
			item.setName(getSingleChild(itemElement, "name").getTextContent().trim());
			
			item.setDailyPrice(
					Double.parseDouble(getSingleChild(itemElement, "dailyPrice").getTextContent().trim().toString()));
			
			item.setCondition(getSingleChild(itemElement, "condition").getTextContent().trim());
			
			itemList.add(item);			
		}

		for (Item itm : itemList) {
			System.out.println(itm);

		}
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}
}
