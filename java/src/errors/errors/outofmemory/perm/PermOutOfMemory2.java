package errors.outofmemory.perm;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class PermOutOfMemory2 {
	public static void main(final String[] args) {
		while (true) {
//			Enhancer enhancer = new Enhancer();
//			enhancer.setSuperclass(OOMObject.class);
//			enhancer.setUseCache(false);
//			enhancer.setCallback(new MethodInterceptor() {
//				@Override
//				public Object intercept(Object o, Method method,
//						Object[] objects, MethodProxy methodProxy)
//						throws Throwable {
//					return methodProxy.invokeSuper(o, args);
//				}
//			});
//			enhancer.create();
			System.out.println("create ok!");
		}
	}

	static class OOMObject {

	}
}