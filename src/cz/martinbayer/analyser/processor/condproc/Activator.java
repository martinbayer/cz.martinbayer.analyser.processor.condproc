package cz.martinbayer.analyser.processor.condproc;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import cz.martinbayer.analyser.processors.IProcessorItemWrapper;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(IProcessorItemWrapper.class.getName(),
				new ConditionProcessorWrapper(), null);
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
