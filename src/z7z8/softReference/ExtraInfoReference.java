package z7z8.softReference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/***
 * 继承自软引用
 * 用来存储额外的信息
 * 
 * @author cash
 * @date 2018年6月1日 下午4:19:10
 * @decription
 */
public class ExtraInfoReference<T> extends SoftReference<T> {

	private Object info;

    public ExtraInfoReference(Object info, T t, ReferenceQueue<T> refQueue) {
        super(t, refQueue);
        this.info = info;
    }

    public Object getExtraInfo() {
        return this.info;
    }

}
