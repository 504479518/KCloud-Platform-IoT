/*
 * Copyright (c) 2022-2025 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.common.statemachine.builder;

import org.laokou.common.statemachine.Action;
import org.laokou.common.statemachine.Condition;
import org.laokou.common.statemachine.State;
import org.laokou.common.statemachine.Transition;
import org.laokou.common.statemachine.impl.StateHelper;
import org.laokou.common.statemachine.impl.TransitionType;

import java.util.Map;

/**
 * TransitionBuilderImpl.
 *
 * @author Frank Zhang 2020-02-07 10:20 PM
 */
class TransitionBuilderImpl<S, E, C> extends AbstractTransitionBuilder<S, E, C>
		implements ExternalTransitionBuilder<S, E, C>, InternalTransitionBuilder<S, E, C> {

	private State<S, E, C> source;

	private Transition<S, E, C> transition;

	public TransitionBuilderImpl(Map<S, State<S, E, C>> stateMap, TransitionType transitionType) {
		super(stateMap, transitionType);
	}

	@Override
	public From<S, E, C> from(S stateId) {
		source = StateHelper.getState(stateMap, stateId);
		return this;
	}

	@Override
	public To<S, E, C> within(S stateId) {
		source = target = StateHelper.getState(stateMap, stateId);
		return this;
	}

	@Override
	public When<S, E, C> when(Condition<C> condition) {
		transition.setCondition(condition);
		return this;
	}

	@Override
	public On<S, E, C> on(E event) {
		transition = source.addTransition(event, target, transitionType);
		return this;
	}

	@Override
	public void perform(Action<S, E, C> action) {
		transition.setAction(action);
	}

}
