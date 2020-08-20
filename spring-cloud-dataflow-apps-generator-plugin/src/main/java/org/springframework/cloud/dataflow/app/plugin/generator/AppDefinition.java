/*
 * Copyright 2020-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.app.plugin.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Tzolov
 */
public class AppDefinition {

	/**
	 * Application name.
	 */
	private String name;

	/**
	 * Application type.
	 */
	private AppType type;

	/**
	 * Application version.
	 */
	private String version;

	/**
	 * Spring Boot version used as a parent by the application.
	 */
	private String springBootVersion;

	/**
	 * Application's backing Spring configuration class.
	 */
	private String configClass;

	/**
	 * Spring Cloud Function defintion.
	 */
	private String functionDefinition;

	/**
	 * Additional application properties.
	 */
	private List<String> properties = new ArrayList<>();

	/**
	 * Maven related configurations to be applied for this App definition.
	 */
	private final Maven maven = new Maven();

	/**
	 * ContainerImage related configurations to be applied for this App definition.
	 */
	private final ContainerImage containerImage = new ContainerImage();

	/**
	 * Application metadata related configurations to be applied for this App definition.
	 */
	private final Metadata metadata = new Metadata();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSpringBootVersion() {
		return springBootVersion;
	}

	public void setSpringBootVersion(String springBootVersion) {
		this.springBootVersion = springBootVersion;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	public Maven getMaven() {
		return maven;
	}

	public ContainerImage getContainerImage() {
		return containerImage;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public boolean isSupplier() {
		return type == AppType.source;
	}

	public boolean isConsumer() {
		return type == AppType.sink;
	}

	public boolean isFunction() {
		return type == AppType.processor;
	}

	public String getConfigClass() {
		return configClass;
	}

	public void setConfigClass(String configClass) {
		this.configClass = configClass;
	}

	public String getFunctionDefinition() {
		return functionDefinition;
	}

	public void setFunctionDefinition(String functionDefinition) {
		this.functionDefinition = functionDefinition;
	}

	public enum ContainerImageFormat {
		/**
		 * Docker image format.
		 */
		Docker,
		/**
		 * OCI format.
		 */
		OCI
	}

	public enum AppType {
		/**
		 * source type.
		 */
		source,
		/**
		 * processor type.
		 */
		processor,
		/**
		 * sink type.
		 */
		sink
	}

	public static class Maven {
		private List<String> dependencyManagement = new ArrayList<>();
		private List<String> dependencies = new ArrayList<>();
		private List<String> plugins = new ArrayList<>();

		public List<String> getDependencyManagement() {
			return dependencyManagement;
		}

		public void setDependencyManagement(List<String> dependencyManagement) {
			this.dependencyManagement = dependencyManagement;
		}

		public List<String> getDependencies() {
			return dependencies;
		}

		public void setDependencies(List<String> dependencies) {
			this.dependencies = dependencies;
		}

		public List<String> getPlugins() {
			return plugins;
		}

		public void setPlugins(List<String> plugins) {
			this.plugins = plugins;
		}
	}

	public static class ContainerImage {
		/**
		 * Allow to generate either Docker or OCI image formats.
		 */
		private ContainerImageFormat format = ContainerImageFormat.Docker;
		/**
		 * True will attempt to inline the (white) filtered Spring Boot metadata as Base64 encoded property.
		 */
		private boolean enableMetadata = false;
		private String orgName = "springcloudstream";
		private String tag = "latest";

		public ContainerImageFormat getFormat() {
			return format;
		}

		public void setFormat(ContainerImageFormat format) {
			this.format = format;
		}

		public boolean isEnableMetadata() {
			return enableMetadata;
		}

		public void setEnableMetadata(boolean enableMetadata) {
			this.enableMetadata = enableMetadata;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}
	}

	public static class Metadata {
		private List<String> sourceTypeFilters = new ArrayList<>();
		private List<String> nameFilters = new ArrayList<>();
		private String mavenPluginVersion;

		public List<String> getSourceTypeFilters() {
			return sourceTypeFilters;
		}

		public void setSourceTypeFilters(List<String> sourceTypeFilters) {
			this.sourceTypeFilters = sourceTypeFilters;
		}

		public List<String> getNameFilters() {
			return nameFilters;
		}

		public void setNameFilters(List<String> nameFilters) {
			this.nameFilters = nameFilters;
		}

		public String getMavenPluginVersion() {
			return mavenPluginVersion;
		}

		public void setMavenPluginVersion(String mavenPluginVersion) {
			this.mavenPluginVersion = mavenPluginVersion;
		}
	}
}
