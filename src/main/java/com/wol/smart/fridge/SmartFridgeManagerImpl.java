package com.wol.smart.fridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class SmartFridgeManagerImpl implements SmartFridgeManager 
{
	private List<SmartFridgeItem> items;
	private List<Long> forgottenItemTypes = null;
	
	public SmartFridgeManagerImpl()
	{
		this.items = new ArrayList<SmartFridgeItem>();
		this.forgottenItemTypes = new ArrayList<Long>();
	}
	
	public void handleItemRemoved(String itemUUID) 
	{
		if(this.items.isEmpty())
		{
			System.out.println("Fridge is currently empty, item [" + itemUUID + "] cannot be removed");
			return;
		}
		
		for(SmartFridgeItem item : this.items)
		{
			if(item.getUUID().equals(itemUUID))
			{
				this.items.remove(item);
			}
		}
	}

	public void handleItemAdded(long itemType, String itemUUID, String name, Double fillFactor) 
	{	
		SmartFridgeItem item = new SmartFridgeItem(itemType, itemUUID, name, fillFactor);
		this.items.add(item);
		System.out.println("Successfully added item :");
		System.out.println(item);
	}

	public Object[] getItems(Double fillFactor) 
	{
		if(true == this.items.isEmpty())
		{
			return null;
		}
		
		Map<Long, List<SmartFridgeItem>> matches = new HashMap<Long, List<SmartFridgeItem>>();
		
		for(SmartFridgeItem item : this.items)
		{
			if(item.getFillFactor() <= fillFactor)
			{
				List<SmartFridgeItem> current = null;
				
				if(false == matches.containsKey(item.getType()))
				{
					current = new ArrayList<SmartFridgeItem>();
				}
				else
				{
					current = matches.get(item.getType());
				}
				
				current.add(item);
				matches.put(item.getType(), current);
			}
		}
		
		Object[] refillable = new Object[matches.values().size()];
		int x = 0;
		
		for(Entry<Long, List<SmartFridgeItem>> entry : matches.entrySet()) {
			refillable[x] = entry.getValue().toString();
		    x++;
		}

		System.out.println("These items need replenishing : ");
		System.out.println(refillable);
		return refillable;
	}

	public Double getFillFactor(long itemType) 
	{
		if(this.items.isEmpty())
		{
			System.out.println("Fridge is currently empty");
			return 0.0;
		}
		
		Double fillFactor = null;
		
		for(SmartFridgeItem item : this.items)
		{
			if(item.getType() == itemType)
			{
				fillFactor = item.getFillFactor();
				break;
			}
		}
		
		return fillFactor;
	}

	public void forgetItem(long itemType) 
	{
		if(this.items.isEmpty())
		{
			System.out.println("Fridge is currently empty, item type [" + itemType + "] cannot be forgot");
			return;
		}
		
		this.forgottenItemTypes.add(itemType);

		for(Iterator<SmartFridgeItem> itemIterator = this.items.iterator(); itemIterator.hasNext();)
		{
			SmartFridgeItem item = itemIterator.next();
			
			if(item.getType() == itemType)
			{
				this.items.remove(item);
			}
		}
	}

	public static void main(String[] args)
	{
		final int maxItems = 5;
		SmartFridgeManagerImpl manager = new SmartFridgeManagerImpl();
		
		for(int x = 0; x < maxItems; x++)
		{
			manager.handleItemAdded(x, UUID.randomUUID().toString().replaceAll("-", "")
					, ("item-" + x), (Double)((double)x / 4));
		}
		
		manager.getItems(1.0);
	}
}
