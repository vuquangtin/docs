package errors.outofmemory;

/**
 * VM args: -Xss128k
 * 
 * @author EMAIL:vuquangtin@gmail.com , tel:0377443333
 * @version 1.0.0
 * @see <a
 *      href="https://github.com/vuquangtin/docs">https://github.com/vuquangtin/docs</a>
 *
 */
public class JavaVMStackSOF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("Stack length: " + oom.stackLength);
			throw e;
		}
	}

	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

}
// crash stack
// Exception in thread "main" java.lang.StackOverflowError
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
// at com.github.quanzhuo.OOM.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:26)
