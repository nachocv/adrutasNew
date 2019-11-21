package com.adrutas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@WebServlet(name = "Prueba", urlPatterns = {"/prueba"})
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	private static final Logger log = Logger.getLogger(ServletInit.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    OutputStream out = response.getOutputStream();
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURL().toString();
			String uri = req.getRequestURI().toString();
			log.fine("URL: " + url + " URI: " + uri);
//		      BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
//		      BlobKey blobKey = blobstoreService.createGsBlobKey(
//		          "/gs/adrutasnew.appspot.com/2019/0819/index.html");
//		      blobstoreService.serve(blobKey, resp);
			Storage storage = StorageOptions.newBuilder()
		            .setProjectId(projectId)
		            .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountJSON)))
		            .build()
		            .getService();
		Blob blob = storage.get(BUCKET_URL, OBJECT_URL);
		String fileContent = new String(blob.getContent());			
			
			Storage storage = StorageOptions.getDefaultInstance().getService();
		    AppIdentityService appIdentityService = AppIdentityServiceFactory.getAppIdentityService();
		    String defaultBucket = appIdentityService.getDefaultGcsBucketName();
//		    GcsFilename gcs_filename = new GcsFilename("adrutasnew.appspot.com", "/2019/0819/index.html");
		    GcsFilename gcs_filename = new GcsFilename(defaultBucket, "/2019/0819/index.html");
			GcsService service = GcsServiceFactory.createGcsService();
			ReadableByteChannel rbc = service.openReadChannel(gcs_filename, 0);
			InputStream in = java.nio.channels.Channels.newInputStream(rbc);
			byte[] buffer = new byte[10240];
		    for (int length = 0; (length = in.read(buffer)) > 0;) {
		        out.write(buffer, 0, length);
		    }
			
//			Storage storage = StorageOptions.getDefaultInstance().getService();
////		    BlobId blobId = BlobId.of("gs://adrutasnew.appspot.com", "/2019/0819/index.html");
//		    BlobId blobId = BlobId.of("adrutasnew.appspot.com", "/2019/0819/index.html");
//		    byte[] content = storage.readAllBytes(blobId);
//		    out.write(content);
//			log.fine("content: " + content);
		} catch (Exception e) {
			log.log(Level.SEVERE,"No lee",e);
		} finally {
	    	out.close();
		}
	}
}