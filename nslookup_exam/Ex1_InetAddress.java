/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author bit
 */
public class Ex1_InetAddress implements Ex1_InetAddressInter{
    
    private InetAddress[] ar;
	@Override
	public void searchDomain(String ns) throws UnknownHostException {
		// TODO Auto-generated method stub
		ar = InetAddress.getAllByName(ns);
	}

	@Override
	public ArrayList<String> getDomainList() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		for(InetAddress e: ar) {
			list.add(e.getHostAddress());
		}
		return list;
	}
    
}
