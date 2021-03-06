package eu.inn.biosign;

/*
 * #%L
 * Java Applet for biometric trait acquisition [http://www.biosignin.org]
 * CmsEncryptor.java is part of BioSignIn project
 * %%
 * Copyright (C) 2014 Innovery SpA
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import eu.inn.biometric.signature.device.CapturingComponent;
import eu.inn.biometric.signature.extendeddata.impl.AdditionalHashInfo;
import eu.inn.biometric.signature.managed.ManagedIsoPoint;
import eu.inn.biometric.signature.managed.impl.CMSEncryptedBdi;
import eu.inn.biometric.signature.managed.impl.IsoSignatureData;
import eu.inn.biosign.util.StaticUtils;
import eu.inn.configuration.BindingData;

public class CmsEncryptor {

	static List<X509Certificate> certs = new ArrayList<X509Certificate>();
	static {
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				try {
					Security.addProvider(new BouncyCastleProvider());
					ClassLoader cl = DeviceManager.class.getClassLoader();
					CertificateFactory cf = CertificateFactory.getInstance("X.509");
					for (String s : StaticUtils.getResourceListing(DeviceManager.class, "encCer/")) {
						System.out.println("Loading certificate: " + s);
						try {
							InputStream inStream = new ByteArrayInputStream(Base64.decode(StaticUtils
									.getResourceUsingFileStreams(cl.getResourceAsStream("encCer/" + s))));
							certs.add((X509Certificate) cf.generateCertificate(inStream));
							inStream.close();
						} catch (Exception ex) {
							System.out.println("Unable to load certificate " + s);
							ex.printStackTrace();
						}
					}
					return null;
				} catch (final Exception e) {
					e.printStackTrace();
					System.out.println("Unable to load BC");
					return null;
				}
			}
		});

	}
	public static Integer ServerKeyLength = 128;

	public static String getBiometricData() {

		return AccessController.doPrivileged(new PrivilegedAction<String>() {
			public String run() {
				try {
					List<ManagedIsoPoint> clearedPenPoints = BioSign._instance.tablet.getClearedPointsForBiometricData();
					CapturingComponent dev = new CapturingComponent();
					BioSign._instance.tablet.populateDeviceInformation(dev);
					IsoSignatureData iso = IsoSignatureData.fromData(clearedPenPoints, dev);
					if (BindingData.hashDocument != null && BindingData.digestAlgoritm != null) {
						AdditionalHashInfo hash = new AdditionalHashInfo();
						hash.setLength(BindingData.count);
						hash.setDigestAlgorithm(BindingData.digestAlgoritm);
						hash.setHashValue(Base64.decode(BindingData.hashDocument));
						hash.setOffset(BindingData.offset);
						iso.getExtendedDatas().add(hash);
					}
					return CMSEncryptedBdi.encrypt(iso, certs, ServerKeyLength).toOutput();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return "ERRORE DURANTE LA GENERAZIONE DEL TRATTO BIOMETRICO CIFRATO";
			}

		});
	}

}
