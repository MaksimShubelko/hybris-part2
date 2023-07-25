package org.personalization.jalo;

import org.personalization.constants.PersonalizationConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

public class PersonalizationManager extends GeneratedPersonalizationManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( PersonalizationManager.class.getName() );
	
	public static final PersonalizationManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (PersonalizationManager) em.getExtension(PersonalizationConstants.EXTENSIONNAME);
	}
	
}
