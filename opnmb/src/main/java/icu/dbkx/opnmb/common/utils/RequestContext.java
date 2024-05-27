package icu.dbkx.opnmb.common.utils;

public class RequestContext {

	private static ThreadLocal<Integer> context = new ThreadLocal<>();
	public static void setContext(Integer context) {
		RequestContext.context.set(context);
	}
	public static Integer getContext() {
		return RequestContext.context.get();
	}
	public static void removeContext() {
		RequestContext.context.remove();
	}
}
