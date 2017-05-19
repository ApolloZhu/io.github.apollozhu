package io.github.apollozhu.util;

import java.util.EventListener;
import java.util.function.Consumer;

import javax.swing.event.EventListenerList;

/**
 * A easy way to become listenable. Could be more pleasant if ****ing Java
 * supports requiring instance variable in interface.
 * 
 * @author Apollo Zhu
 * 
 * @version 1.0
 */
@FunctionalInterface
public interface AZListenable<Listener extends EventListener> {

	/**
	 * Suggested implementation:
	 * 
	 * <pre>
	 * <code>import javax.swing.event.EventListenerList;
	 * // ...
	 *private EventListenerList list = new EventListenerList();
	 *{@literal @}Override
	 *public EventListenerList getListenerList() {
	 *    return list;
	 *}</code>
	 * </pre>
	 * 
	 * @return the persisted event listener list.
	 */
	EventListenerList getListenerList();

	@SuppressWarnings("unchecked")
	default Class<Listener> getListenerClass() {
		return (Class<Listener>) EventListener.class;
	}

	default void addListener(Listener l) {
		getListenerList().add(getListenerClass(), l);
	}

	default void removeListener(Listener l) {
		getListenerList().remove(getListenerClass(), l);
	}

	default void forEachListener(Consumer<Listener> consumer) {
		for (Listener listener : getListenerList().getListeners(getListenerClass()))
			consumer.accept(listener);
	}

}
