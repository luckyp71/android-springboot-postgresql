package com.example.demo.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;

public class Info extends Thread {
	
	private String name;
	
	public Info(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			getUrls(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUrls(String name) throws IOException {
		Document doc = Jsoup.connect("https://www.google.com/search?q="+name).get();
		Elements urls = doc.select("a[href]");
		Collection<String> collections = new ArrayList<>();
		urls.stream().forEach(url -> {
			URL u = null;
			try {
				u = new URL(url.absUrl("href"));
				String urlString = u.getHost();
				InetAddress ip = InetAddress.getByName(urlString);
				String hostname = ip.getHostName();
				collections.add(hostname);
			} catch (MalformedURLException | UnknownHostException e) {
				e.printStackTrace();
			}
		});
		Collection<String> uniqueHostname = collections.parallelStream().distinct().map(i -> i).collect(Collectors.toList());
		uniqueHostname.parallelStream().forEach(i -> {
			System.out.println(i);
		});
	}
	
}
