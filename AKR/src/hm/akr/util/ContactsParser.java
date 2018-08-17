package hm.akr.util;

import hm.akr.common.BeanUtil;
import hm.akr.dto.CustomerDTO;
import hm.akr.interfaces.Customer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * parser class that reads/writes the contacts from the local machine as well as remote 
 * server
 * 
 * @version 2.0
 *
 */
public class ContactsParser {

	//public static final String CUSTOMER_FILE_NAME = "hm/akr/resources/customer.xml";
	public static final String CUSTOMER_FILE_NAME = "/lib/customer.xml";
	public static final String CONTACTS_NODE = "Contacts";
	public static final String CONTACT_NODE = "Contact";
	public static final String NAME_NODE = "Name";
	public static final String ADDRESS_NODE = "Address";
	public static final String TYPE_NODE = "Type";

	public static final String CONSIGNOR = "Consignor";
	public static final String CONSIGNEE = "Consignee";

	BeanUtil beanutil = null;

	Customer remote = null;

	String stationCode = null;

	CustomerDTO dto = null;

	public ContactsParser() {
		try {
			beanutil = BeanUtil.getInstance();
			if (null != beanutil)
				remote = beanutil.getCustomerBean();
			if (null != remote)
				stationCode = beanutil.getActualStationCode();
		} catch (NamingException namingexception) {

			namingexception.printStackTrace();
		} catch (Exception exception) {

			exception.printStackTrace();
		}
	}

	/**
	 * This method retrieves the contacts from the xml file that is store locally.
	 * 
	 * @return Contact[] Array of Contact Instance. Which fetches both the consignee 
	 * 					 and consignor contacts.
	 */
	public ArrayList<Contact> populateContactsFromLocal() {
		ArrayList<Contact> contacts = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setCoalescing(true); // Convert CDATA to Text nodes
		factory.setNamespaceAware(false); // No namespaces: this is default
		factory.setValidating(false); // Don't validate DTD: also default

		DocumentBuilder parser = null;
		InputStream stream = null;
		Document document = null;

		try {
			parser = factory.newDocumentBuilder();

			
			String curDir = System.getProperty("user.dir");		
			String dirLoc = curDir + CUSTOMER_FILE_NAME;
			
			//stream = ContactsParser.class.getClassLoader().getResourceAsStream(dirLoc);
			document = parser.parse(new File(dirLoc));
			//document = parser.parse(new File(CUSTOMER_FILE_NAME));

			NodeList sections = document.getElementsByTagName(CONTACT_NODE);
			int numSections = sections.getLength();

			if (numSections > 0) {
				contacts = new ArrayList<Contact>();

				Node name = null;
				Contact contact = null;
				for (int i = 0; i < numSections; i++) {
					Node contactNode = (Node) sections.item(i);
					name = contactNode.getFirstChild();
					contact = new Contact();
					contact.setId(i + 1);
					contact.setName(name.getTextContent());
					contact.setAddress(name.getNextSibling().getTextContent());
					if (CONSIGNEE.equals(contactNode.getLastChild()
							.getTextContent())) {
						contact.setConsignee(true);
					}
					contacts.add(contact);
				}
			}

		} catch (Exception exception) {

		} finally {
			parser = null;
			try {
				if (null != stream)
					stream.close();
			} catch (IOException e) {
			}
			document = null;
		}

		return contacts;
	}

	/**
	 * This method retrieves the contacts from the remote server.
	 * 
	 * @return Contact[] Array of Contact Instance. Which fetches both the consignee 
	 * 					 and consignor contacts.
	 */
	public Contact[] populateContactsFromRemote() {
		return null;
	}

	/**
	 * This method stores the contacts to the local file.
	 * 
	 */
	public void storeContactsToLocal(Contact[] contacts) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document document = db.newDocument();

		Element root = document.createElement(CONTACTS_NODE);
		//document.appendChild(root);                    

		int length = contacts.length;
		for (int i = 0; i < length; i++) {

			Element contact = document.createElement(CONTACT_NODE);

			Element name = document.createElement(NAME_NODE);
			name.appendChild((document.createTextNode(contacts[i].getName())));

			Element address = document.createElement(ADDRESS_NODE);
			address.appendChild(document.createTextNode((contacts[i]
					.getAddress())));

			Element type = document.createElement(TYPE_NODE);
			if (contacts[i].isConsignee())
				type.appendChild(document.createTextNode(CONSIGNEE));
			else
				type.appendChild(document.createTextNode(CONSIGNOR));

			contact.appendChild(name);
			contact.appendChild(address);
			contact.appendChild(type);

			root.appendChild(contact);
		}
		document.appendChild(root);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		
		//File customerFile = new File(CUSTOMER_FILE_NAME);

		//StreamResult result = new StreamResult(customerFile);		
		
		File customerFile = new File("lib/customer.xml");
		StreamResult result = new StreamResult(customerFile);	
		
		transformer.transform(source, result);
	}

	/**
	 * This method to add the contacts to the remote database. This method calls
	 * the customer bean to achieve this.
	 * 
	 * @param contact An instance of Contact Dto that needs to be added.
	 * 
	 **/
	public boolean addContactToRemote(Contact contact) throws Exception {
		boolean status = false;
		if (null != contact) {
			if (contact.isConsignee()) {
				try {
					//if (null != remote)
						//status = remote.addConsignee(contact.getName(), contact.getAddress(), stationCode);

				} catch (Exception exception) {
					exception.printStackTrace();
					throw exception;
				}
			} else {
				try {
					if (null != remote) {
						dto = new CustomerDTO();
						dto.setName(contact.getName());
						dto.setAddress(contact.getAddress());
						status = remote.addCustomer(dto);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					throw exception;
				}
			}
		}
		return status;

	}

	/**
	 * This method updates the existing customer details. 
	 */
	public void updateContactToRemote(Contact contact) throws Exception {

	}

	/**
	 * This method deletes the existing customer for this station.
	 **/
	public boolean deleteContactToRemote(Contact contact) throws Exception {
		boolean status = false;
		if (null != contact) {
			if (contact.isConsignee()) {
				try {
					//if (null != remote)
						//status = remote.deleteConsignee(contact.getName());
				} catch (Exception exception) {
					exception.printStackTrace();
					throw exception;
				}
			} else {
				try
				{
					if (null != remote)
						status = remote.deleteCustomer(contact.getName());
						
				}catch (Exception exception) {
					exception.printStackTrace();
					throw exception;
				}
				

			}
		}

		return status;
	}
}
