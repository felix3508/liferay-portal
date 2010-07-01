/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.test.TestCase;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <a href="FutureClusterResponseTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class FutureClusterResponseTest extends TestCase {

	public void testMultipleResponseFailure() {

		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));
		addresses.add(new MockAddress("1.2.3.5"));
		addresses.add(new MockAddress("1.2.3.6"));

		FutureClusterResponses responses = new FutureClusterResponses(
			addresses);

		ClusterNodeResponse clusterNodeResponse = new ClusterNodeResponse();
		clusterNodeResponse.setClusterNode(new ClusterNode("1.2.3.4"));
		responses.addClusterNodeResponse(clusterNodeResponse);


		ClusterNodeResponse clusterNodeResponse1 = new ClusterNodeResponse();
		clusterNodeResponse1.setClusterNode(new ClusterNode("1.2.3.5"));
		responses.addClusterNodeResponse(clusterNodeResponse1);

		try {
			responses.get(500, TimeUnit.MILLISECONDS);
			fail("Should have failed");
		}
		catch (InterruptedException e) {
			fail("Getting interrupted");
		}
		catch (TimeoutException e) {
		}
	}

	public void testMultipleResponseSuccess() {

		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));
		addresses.add(new MockAddress("1.2.3.5"));
		addresses.add(new MockAddress("1.2.3.6"));

		FutureClusterResponses responses = new FutureClusterResponses(
			addresses);

		ClusterNodeResponse clusterNodeResponse = new ClusterNodeResponse();
		clusterNodeResponse.setClusterNode(new ClusterNode("1.2.3.4"));
		responses.addClusterNodeResponse(clusterNodeResponse);


		ClusterNodeResponse clusterNodeResponse1 = new ClusterNodeResponse();
		clusterNodeResponse1.setClusterNode(new ClusterNode("1.2.3.5"));
		responses.addClusterNodeResponse(clusterNodeResponse1);

		ClusterNodeResponse clusterNodeResponse2 = new ClusterNodeResponse();
		clusterNodeResponse2.setClusterNode(new ClusterNode("1.2.3.6"));
		responses.addClusterNodeResponse(clusterNodeResponse2);

		try {
			responses.get(500, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e) {
			fail("Getting interrupted");
		}
		catch (TimeoutException e) {
			fail("Should not have timed out");
		}
	}

	public void testSingleResponseFailure() {

		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));

		FutureClusterResponses responses = new FutureClusterResponses(
			addresses);

		try {
			responses.get(500, TimeUnit.MILLISECONDS);
			fail("Should have failed");
		}
		catch (InterruptedException e) {
			fail("Getting interrupted");
		}
		catch (TimeoutException e) {
		}
	}

	public void testSingleResponseSuccess() {

		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));

		FutureClusterResponses responses = new FutureClusterResponses(
			addresses);

		ClusterNodeResponse clusterNodeResponse = new ClusterNodeResponse();
		clusterNodeResponse.setClusterNode(new ClusterNode("test"));
		responses.addClusterNodeResponse(clusterNodeResponse);

		try {
			responses.get(500, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e) {
			fail("Getting interrupted");
		}
		catch (TimeoutException e) {
			fail("Should not have timed out");
		}
	}

	private class MockAddress implements Address {
		public MockAddress(String address) {
			_address = address;
		}

		public String getDescription() {
			return _address;
		}

		public Object getRealAddress() {
			return _address;
		}

		private String _address;
	}
}
