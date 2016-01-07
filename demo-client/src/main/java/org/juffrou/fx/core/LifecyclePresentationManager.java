package org.juffrou.fx.core;


public interface LifecyclePresentationManager<T> {
	
	/**
	 * Transforms a traditional Java Bean into a JavaFX Bean and binds it to the user interface.
	 * @param sourceDomain the domain to bind to the user interface. Must not be a JFXProxy.
	 */
	public void setLifecycleModelSource(T sourceDomain);
	
	/**
	 * Transforms the JFXProxy bound to the UI into its original Java Bean and returns it.
	 * @return The original Java Bean with its properties changes as expected.
	 */
	public T getLifecycleModelSource();
	
	/**
	 * Instantiates a new empty source domains and binds it to the UI
	 */
	public void createNewTransient();
	
	/**
	 * Asks the PresentationManager to persist the item being displayed
	 */
	public void save();
	
	/**
	 * Asks the PresentationManager to perform a search and displays the result in a table where each row is a domain
	 */
	public void search();
	
	/**
	 * Choose one domain from the serach list to bind to the UI. The presentation manager will load this item from the database again and initialize all properties necessary for binding.
	 * @param item item from the search list to bind to.
	 */
	public void setSearchItem(T item);
	
	
	/**
	 * Cancel whatever action and return the form to the original state
	 */
	public void cancel();
}
