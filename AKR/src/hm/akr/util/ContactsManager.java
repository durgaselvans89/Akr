package hm.akr.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * @version 2.0
 * 
 */
public class ContactsManager {

	private ArrayList<Contact> contactsList = null;

	private ContactsParser parser = null;

	/**
	 * Constructor Method
	 */
	public ContactsManager() {
		parser = new ContactsParser();
		contactsList = parser.populateContactsFromLocal();
		if (null == contactsList)
			contactsList = new ArrayList<Contact>();

	}

	/**
	 * Method to retrieve the consignee contact list. Method searches the global
	 * contact list for consignee details
	 * 
	 * @return Contact[] Array of Contact which has name, address and a boolean.
	 */
	public Contact[] getConsigneeContactList() {
		if (null != contactsList) {
			ArrayList<Contact> consigneeList = new ArrayList<Contact>();

			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee()) {
					consigneeList.add(contact);
				}
			}
			size = consigneeList.size();
			if (size > 0) {
				return consigneeList.toArray(new Contact[size]);
			}
		}

		return null;
	}

	/**
	 * Method to retrieve the consignor contact list. Method searches the global
	 * contact list for consignor details
	 * 
	 * @return Contact[] Array of Contact which has name, address and a boolean.
	 */
	public Contact[] getConsignorContactList() {
		if (null != contactsList) {
			ArrayList<Contact> consignorList = new ArrayList<Contact>();

			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee()) {
					consignorList.add(contact);
				}
			}
			size = consignorList.size();
			if (size > 0) {
				return consignorList.toArray(new Contact[size]);
			}
		}

		return null;
	}

	/**
	 * Method to get the consignee by consignee name.
	 * 
	 * @param name
	 *            Consignee Name. This parameter should not be null.
	 * 
	 * @return Contact An instance of contact.
	 */
	public Contact getConsigneeByName(String name) {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;
			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee() && name.equals(contact.getName())) {
					return contact;
				}
			}
		}

		return null;
	}

	/**
	 * Method to get the consignor by consignor name.
	 * 
	 * @param name
	 *            Consignor Name. This parameter should not be null.
	 * 
	 * @return Contact An instance of contact.
	 */
	public Contact getConsignorByName(String name) {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;
			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee() && name.equals(contact.getName())) {
					return contact;
				}
			}
		}

		return null;
	}

	/**
	 * Method to retrieve the consignee name list.
	 * 
	 * @return String[] returns all the consignee name.
	 */
	public String[] getConsigneeNameList() {
		if (null != contactsList) {
			ArrayList<String> consigneeList = new ArrayList<String>();

			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee()) {
					consigneeList.add(contact.getName());
				}
			}
			size = consigneeList.size();
			if (size > 0) {
				return consigneeList.toArray(new String[size]);
			}
		}

		return null;
	}

	/**
	 * Method to retrieve the consignor name list.
	 * 
	 * @return String[] returns all the consignor name.
	 */
	public String[] getConsignorNameList() {

		if (null != contactsList) {
			ArrayList<String> consignorList = new ArrayList<String>();

			int size = contactsList.size();

			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee()) {
					consignorList.add(contact.getName());
				}
			}
			size = consignorList.size();
			if (size > 0) {
				return consignorList.toArray(new String[size]);
			}
		}

		return null;
	}

	/**
	 * Method to retrieve the Consignor Address by Consignor Name.
	 * 
	 * @param consignorName
	 *            Consignor Name
	 * 
	 * @return String Consignor Address
	 * @throws NullPointerException
	 *             if consignorName is null.
	 */
	public String getConsignorAddressByName(String consignorName) {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;
			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee()
						&& consignorName.equals(contact.getName())) {
					return contact.getAddress();
				}
			}
		}
		return null;
	}

	/**
	 * Method to retrieve the Consignee Address by consignee name.
	 * 
	 * @param consigneeName
	 *            Consignee Name
	 * 
	 * @return String Consignee Address
	 * @throws NullPointerException
	 *             if consigneeName is null.
	 */
	public String getConsigneeAddressByName(String consigneeName) {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;
			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee()
						&& consigneeName.equals(contact.getName())) {
					return contact.getAddress();
				}
			}
		}

		return null;
	}

	/**
	 * Method to get both consignee and consignor contact.
	 * 
	 * @return Contact[] Returns null if there is no contacts.
	 */
	public Contact[] getContactList() {
		if (null != contactsList) {
			int size = contactsList.size();
			return contactsList.toArray(new Contact[size]);
		}
		return null;
	}

	/**
	 * Method to add consignee. This method throws exception if the consignee
	 * name already exists.
	 * 
	 * @param name
	 *            Consignee Name
	 * @param address
	 *            Consignee Address
	 * @throws Exception
	 *             If the consignee name already exists.
	 * 
	 */
	public void addConsignee(String name, String address) throws Exception {
		Contact consignee = getConsigneeByName(name);
		if (null != consignee) {
			contactsList.remove(consignee);
			consignee = new Contact();
			consignee.setName(name);
			consignee.setAddress(address);
			consignee.setConsignee(true);
			Random rand = new Random();
			consignee.setId(rand.nextInt(1024) * address.length());
			// parser.addContactToRemote(consignee);
			contactsList.add(consignee);
			shutdown();

		} else {
			consignee = new Contact();
			consignee.setName(name);
			consignee.setAddress(address);
			consignee.setConsignee(true);

			Random rand = new Random();
			consignee.setId(rand.nextInt(1024) * address.length());
			// parser.addContactToRemote(consignee);
			contactsList.add(consignee);
			shutdown();
		}
	}

	/**
	 * Method to add consignor. This method throws exception if the consignor
	 * name already exists.
	 * 
	 * @param name
	 *            Consignor Name
	 * @param address
	 *            Consignor Address
	 * @throws Exception
	 *             If the consignor name already exists.
	 */
	public void addConsignor(String name, String address) throws Exception {
		Contact consignor = getConsignorByName(name);
		if (null != consignor) {

			contactsList.remove(consignor);
			consignor = new Contact();
			consignor.setName(name);
			consignor.setAddress(address);
			consignor.setConsignee(false);

			Random rand = new Random();
			consignor.setId(rand.nextInt(1024) * address.length());
			// parser.addContactToRemote(consignor);
			contactsList.add(consignor);
			shutdown();

		} else {
			consignor = new Contact();
			consignor.setName(name);
			consignor.setAddress(address);
			consignor.setConsignee(false);

			Random rand = new Random();
			consignor.setId(rand.nextInt(1024) * address.length());
			// parser.addContactToRemote(consignor);
			contactsList.add(consignor);
			shutdown();
		}
	}

	/**
	 * Method to edit the consignee by name. This method updates the address for
	 * the given consignee name.
	 * 
	 * @param name
	 *            Consignee name
	 * @param address
	 *            Consignee address
	 * 
	 * @throws Exception
	 *             If the updation failed on the remote server.
	 */
	public void editConsigneeByName(String name, String address)
			throws Exception {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee() && name.equals(contact.getName())) {
					contact.setAddress(address);
					// parser.updateContactToRemote(contact);
					contactsList.set(i, contact);
					shutdown();
					return;
				}
			}
		}
	}

	/**
	 * Method to edit the consignor by name. This method updates the address for
	 * the given consignor name.
	 * 
	 * @param name
	 *            Consignor name
	 * @param address
	 *            Consignor address
	 * 
	 * @throws Exception
	 *             If the updation failed on the remote server.
	 */
	public void editConsignorByName(String name, String address)
			throws Exception {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee() && name.equals(contact.getName())) {
					contact.setAddress(address);
					// parser.updateContactToRemote(contact);
					contactsList.set(i, contact);
					shutdown();
					return;
				}
			}
		}
	}

	/**
	 * Method to delete the consignee by name.
	 * 
	 */
	public void deleteConsigneeByName(String name) throws Exception {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (contact.isConsignee() && name.equals(contact.getName())) {
					// parser.deleteContactToRemote(contact);
					contactsList.remove(i);
					shutdown();
					return;
				}
			}
		}
	}

	/**
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void deleteConsignorByName(String name) throws Exception {
		if (null != contactsList) {
			int size = contactsList.size();
			Contact contact = null;

			for (int i = 0; i < size; i++) {
				contact = contactsList.get(i);
				if (!contact.isConsignee() && name.equals(contact.getName())) {
					// parser.deleteContactToRemote(contact);
					contactsList.remove(i);
					shutdown();
					return;
				}
			}
		}
	}

	/**
	 * 
	 */
	public void shutdown() {
		int size = 0;
		if (null != contactsList && (size = contactsList.size()) >= 0)
			try {
				parser.storeContactsToLocal(contactsList
						.toArray(new Contact[size]));
			} catch (Exception e) {
				e.printStackTrace();

			}
	}
}
