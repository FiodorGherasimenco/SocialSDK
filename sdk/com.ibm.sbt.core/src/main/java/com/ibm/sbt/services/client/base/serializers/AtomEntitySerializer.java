/*
 * � Copyright IBM Corp. 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package com.ibm.sbt.services.client.base.serializers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ibm.sbt.services.client.base.AtomEntity;
import com.ibm.sbt.services.client.base.ConnectionsConstants.Namespaces;

/**
 * @author Mario Duarte
 *
 */
public class AtomEntitySerializer<T extends AtomEntity> extends BaseEntitySerializer<T> {
	
	private static SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");	

	public AtomEntitySerializer(T entity) {
		super(entity);
	}
	
	public Node genericAtomEntry() {
		Node entry = entry();
		
		appendChildren(entry, 
			title(),
			id(),
			summary(),
			content()
		);
		
		appendChildren(entry, tags());

		return entry;
	}

	protected Node entry() {
		return rootNode(element(Namespaces.ATOM, "entry"));
	}
	
	protected Element title() {
		return textElement("title", entity.getTitle(), attribute("type", "text"));
	}
	
	protected Element id() {
		return textElement("id", entity.getId());
	}
	
	protected Element published() {
		return textElement("published", DateSerializer.toString(dateFormat, entity.getPublished()));
	}
	
	protected Element updated() {
		return textElement("updated", DateSerializer.toString(dateFormat, entity.getUpdated()));
	}
	
	protected Element summary() {
		return textElement("summary", entity.getSummary(), attribute("type", "text"));
	}
	
	protected Element content() {
		return textElement("content", entity.getContent(), attribute("type", "text"));
	}
	
	protected Node author() {
		return (new PersonSerializer(entity.getAuthor())).xmlNode("author");
	}
	
	protected Node contributor() {
		return (new PersonSerializer(entity.getContributor())).xmlNode("contributor");
	}

	protected Element categoryType(String type) {
		return element("category", 
				attribute("scheme", "tag:ibm.com,2006:td/type"), 
				attribute("term", type), 
				attribute("label", type));
	}
	
	protected List<Element> tags() {
		List<Element> elements = new ArrayList<Element>();
		if(entity.getBaseTags() != null) {
			List<String> list = entity.getBaseTags();
			for(String tag : list) {
				elements.add(element("category", 
						attribute("term", tag),
						attribute("label", tag)));
			}
		}
		return elements;
	}
}
