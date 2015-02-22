package org.juffrou.fx.core;


public interface LifecyclePresentationManager {

	/**
	 * Asks the PresentationManager to persist the item being displayed
	 */
	public void save();
	
	/**
	 * Asks the PresentationManager to perform a search
	 */
	public void search();
	
	/**
	 * 
	 * @param item
	 */
	public void selectSearchItem(Object item);
	
	public void cancel();
}
