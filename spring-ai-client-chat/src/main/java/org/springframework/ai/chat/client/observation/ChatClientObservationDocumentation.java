/*
 * Copyright 2023-2024 the original author or authors.
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

package org.springframework.ai.chat.client.observation;

import io.micrometer.common.docs.KeyName;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import io.micrometer.observation.docs.ObservationDocumentation;
import org.springframework.ai.observation.conventions.AiObservationAttributes;

/**
 * Documented conventions for chat client observations.
 *
 * @author Christian Tzolov
 * @since 1.0.0
 */
public enum ChatClientObservationDocumentation implements ObservationDocumentation {

	/**
	 * AI Chat Client observations
	 */
	AI_CHAT_CLIENT {
		@Override
		public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
			return DefaultChatClientObservationConvention.class;
		}

		@Override
		public KeyName[] getLowCardinalityKeyNames() {
			return LowCardinalityKeyNames.values();
		}

		@Override
		public KeyName[] getHighCardinalityKeyNames() {
			return HighCardinalityKeyNames.values();
		}

	};

	public enum LowCardinalityKeyNames implements KeyName {

		/**
		 * Spring AI kind.
		 */
		SPRING_AI_KIND {
			@Override
			public String asString() {
				return "spring.ai.kind";
			}
		},

		/**
		 * Is the chat model response a stream.
		 */
		STREAM {
			@Override
			public String asString() {
				return "spring.ai.chat.client.stream";
			}
		}

	}

	public enum HighCardinalityKeyNames implements KeyName {

		/**
		 * List of configured chat client advisors.
		 */
		CHAT_CLIENT_ADVISORS {
			@Override
			public String asString() {
				return "spring.ai.chat.client.advisors";
			}
		},

		/**
		 * The identifier of the conversation.
		 */
		CHAT_CLIENT_CONVERSATION_ID {
			@Override
			public String asString() {
				return "spring.ai.chat.client.conversation.id";
			}
		},

		// Request

		/**
		 * Names of the tools made available to the chat client.
		 */
		CHAT_CLIENT_TOOL_NAMES {
			@Override
			public String asString() {
				return "spring.ai.chat.client.tool.names";
			}
		},

		/**
		 * Enabled tool function names.
		 * @deprecated replaced by {@link #CHAT_CLIENT_TOOL_NAMES}
		 */
		@Deprecated
		CHAT_CLIENT_TOOL_FUNCTION_NAMES {
			@Override
			public String asString() {
				return "spring.ai.chat.client.tool.function.names";
			}
		},

		/**
		 * List of configured chat client function callbacks.
		 * @deprecated replaced by {@link #CHAT_CLIENT_TOOL_NAMES}
		 */
		@Deprecated
		CHAT_CLIENT_TOOL_FUNCTION_CALLBACKS {
			@Override
			public String asString() {
				return "spring.ai.chat.client.tool.function.callbacks";
			}
		},

		/**
		 * Map of advisor parameters.
		 * @deprecated risk to expose sensitive information or break the instrumentation
		 * since the advisor context map is used to pass arbitrary Java objects between
		 * advisors and not necessarily serializable. The conversation ID, previously part
		 * of this, is already included in the {@link #CHAT_CLIENT_CONVERSATION_ID}
		 * method.
		 */
		@Deprecated
		CHAT_CLIENT_ADVISOR_PARAMS {
			@Override
			public String asString() {
				return "spring.ai.chat.client.advisor.params";
			}
		},

		/**
		 * Chat client user text.
		 * @deprecated replaced by {@link #PROMPT}
		 */
		@Deprecated
		CHAT_CLIENT_USER_TEXT {
			@Override
			public String asString() {
				return "spring.ai.chat.client.user.text";
			}
		},

		/**
		 * Chat client user parameters.
		 * @deprecated replaced by {@link #PROMPT}
		 */
		@Deprecated
		CHAT_CLIENT_USER_PARAMS {
			@Override
			public String asString() {
				return "spring.ai.chat.client.user.params";
			}
		},

		/**
		 * Chat client system text.
		 * @deprecated replaced by {@link #PROMPT}
		 */
		@Deprecated
		CHAT_CLIENT_SYSTEM_TEXT {
			@Override
			public String asString() {
				return "spring.ai.chat.client.system.text";
			}
		},

		/**
		 * Chat client system parameters.
		 * @deprecated replaced by {@link #PROMPT}
		 */
		@Deprecated
		CHAT_CLIENT_SYSTEM_PARAM {
			@Override
			public String asString() {
				return "spring.ai.chat.client.system.params";
			}
		},

		// Content

		/**
		 * The full prompt requested to be sent to the model.
		 */
		PROMPT {
			@Override
			public String asString() {
				return AiObservationAttributes.PROMPT.value();
			}
		},

	}

}
