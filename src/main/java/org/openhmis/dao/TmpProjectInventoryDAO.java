package org.openhmis.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openhmis.domain.TmpProjectInventory;

public class TmpProjectInventoryDAO extends BaseDAO {

	// default constructor
	public TmpProjectInventoryDAO() { }

	public TmpProjectInventory getTmpProjectInventoryById(Integer inventoryId)  {
		String queryString = "select projectInventory " + 
			"from TmpProjectInventory as projectInventory " + 
			"where projectInventory.inventoryId =:inventoryId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("inventoryId", inventoryId);
		queryObject.setMaxResults(1);
		
		List<TmpProjectInventory> results = queryObject.list();
		session.close();
		
		if(results.size() > 0)
			return (TmpProjectInventory)results.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectInventory> getTmpProjectInventories() {
		String queryString = "select projectInventory " + 
				"from TmpProjectInventory as projectInventory";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		List<TmpProjectInventory> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectInventory> getTmpProjectInventories(Date updateDate) {
		String queryString = "select projectInventory " + 
				"from TmpProjectInventory as projectInventory " + 
				"where projectInventory.updateDate >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectInventory> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectInventory> getTmpProjectInventoriesByProjectCoCId(Integer projectCoCid) {
		String queryString = "select projectInventory " + 
				"from TmpProjectInventory as projectInventory " + 
				"where projectInventory.projectCoCid =:projectCoCid";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectCoCid", projectCoCid);
		List<TmpProjectInventory> results = queryObject.list();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpProjectInventory> getTmpProjectInventoriesByProjectCoCId(Integer projectCoCid, Date updateDate) {
		String queryString = "select projectInventory " + 
				"from TmpProjectInventory as projectInventory " + 
				"where projectInventory.projectCoCid =:projectCoCid " + 
				"  and projectInventory.updateDate >= :updatedSince";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("projectCoCid", projectCoCid);
		queryObject.setParameter("updatedSince", updateDate);
		List<TmpProjectInventory> results = queryObject.list();
		session.close();
		return results;
	}
}