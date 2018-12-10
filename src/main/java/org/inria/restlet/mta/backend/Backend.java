package org.inria.restlet.mta.backend;

/**
 *
 * Backend for all resources.
 * 
 * @author ctedeschi
 * @author msimonin
 *
 */
public class Backend
{
    /** Database.*/
    private SimulationParc parc_;

    public Backend()
    {
        parc_ = new SimulationParc();
        parc_.fonctionner();
    }

    public SimulationParc getParc_() {
        return parc_;
    }
}
