/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springmodules.lucene.search.factory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;

/**
 * Abstract class of every multiple searcher factories to
 * help to configure and search multiple indexes.
 * 
 * @author Thierry Templier
 */
public abstract class AbstractMultipleSearcherFactory {

	private List directories;

	/**
	 * Return the Lucene Directories used by index factories.
	 */
	public List getDirectories() {
		return directories;
	}

	/**
	 * Set the Lucene Directories to be used.
	 */
	public void setDirectories(List directories) {
		this.directories = directories;
	}

	/**
	 * This method creates all the searchers for every configured
	 * Lucene directories to search.
	 * @return the searchers on every directories
	 * @throws IOException if thrown by Lucene methods
	 */
	protected Searcher[] createSearchers() throws IOException {
		Searcher[] searchers=new Searcher[directories.size()];
		int cpt=0;
		for(Iterator iterator=directories.iterator();iterator.hasNext();) {
			searchers[cpt]=new IndexSearcher((Directory)iterator.next());
			cpt++; 
		}
		return searchers;
	}

}