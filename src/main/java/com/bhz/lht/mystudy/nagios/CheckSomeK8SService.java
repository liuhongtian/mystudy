package com.bhz.lht.mystudy.nagios;

public class CheckSomeK8SService {

	/**
	 * a nagios plugin sample
	 * 
	 * @see <a href=
	 *      "https://assets.nagios.com/downloads/nagioscore/docs/nagioscore/4/en/pluginapi.html">https://assets.nagios.com/downloads/nagioscore/docs/nagioscore/4/en/pluginapi.html</a>
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Service unreachable!");
//		System.exit(1);
		System.out.println("Service OK!");
		System.exit(0);
	}

}
