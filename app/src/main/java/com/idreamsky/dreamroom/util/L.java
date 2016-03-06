package com.idreamsky.dreamroom.util;

import android.util.Log;

/**
 * 日志控制类
 */
public class L {
	/** 日志输出时的TAG */
	private static String mTag = "print";
	/** 日志输出级别NONE */
	public static final int LEVEL_OFF = 0;
	/** 日志输出级别ALL */
	public static final int LEVEL_ALL = 7;

	/** 日志输出级别S,自定义定义的一个级别 */
	public static final int LEVEL_SYSTEM = 6;
	/** 日志输出级别V */
	public static final int LEVEL_VERBOSE = 5;
	/** 日志输出级别D */
	public static final int LEVEL_DEBUG = 4;
	/** 日志输出级别I */
	public static final int LEVEL_INFO = 3;
	/** 日志输出级别W */
	public static final int LEVEL_WARN = 2;
	/** 日志输出级别E */
	public static final int LEVEL_ERROR = 1;


	/** 是否允许输出log */
	private static int mDebuggable = LEVEL_ALL;

	/** 用于记时的变量 */
	private static long mTimestamp = 0;
	/** 写文件的锁对象 */
	private static final Object mLogLock = new Object();

	/**---------------日志输出,已固定TAG  begin---------------**/
	/** 以级别为 v 的形式输出LOG */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/** 以级别为 d 的形式输出LOG */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	/** 以级别为 i 的形式输出LOG */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出LOG */
	public static void w(String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出Throwable */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/** 以级别为 w 的形式输出LOG信息和Throwable */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/** 以级别为 e 的形式输出LOG */
	public static void e(String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}

	/** 以级别为 e 的形式输出Throwable */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}

	/** 以级别为 s 的形式输出LOG,主要是为了System.out.println,稍微格式化了一下 */
	public static void sf(String msg) {
		if (mDebuggable >= LEVEL_SYSTEM) {
			System.out.println("----------" + msg + "----------");
		}
	}

	/** 以级别为 s 的形式输出LOG,主要是为了System.out.println */
	public static void s(String msg) {
		if (mDebuggable >= LEVEL_SYSTEM) {
			System.out.println(msg);
		}
	}

	/**---------------日志输出,已固定TAG  end---------------**/

	/**---------------日志输出,未固定TAG  begin---------------**/
	/** 以级别为 d 的形式输出LOG */
	public static void v(String tag, String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(tag, msg);
		}
	}

	/** 以级别为 d 的形式输出LOG */
	public static void d(String tag, String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(tag, msg);
		}
	}

	/** 以级别为 i 的形式输出LOG */
	public static void i(String tag, String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(tag, msg);
		}
	}

	/** 以级别为 w 的形式输出LOG */
	public static void w(String tag, String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(tag, msg);
		}
	}

	/** 以级别为 e 的形式输出LOG */
	public static void e(String tag, String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(tag, msg);
		}
	}

}
