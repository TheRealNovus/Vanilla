/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.protocol.codec.player;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.json.JSONException;
import org.json.JSONObject;

import org.spout.api.protocol.MessageCodec;
import org.spout.api.util.ChannelBufferUtils;

import org.spout.vanilla.protocol.msg.player.PlayerChatMessage;

public final class PlayerChatCodec extends MessageCodec<PlayerChatMessage> {
	public PlayerChatCodec() {
		super(PlayerChatMessage.class, 0x03);
	}

	@Override
	public PlayerChatMessage decode(ChannelBuffer buffer) {
		String message = ChannelBufferUtils.readString(buffer);

		//This is basicly to make the unit tests works. Else it just crash :(
		try {
			return new PlayerChatMessage((String)new JSONObject(message).get("text"));
		} catch (JSONException e) {
			return new PlayerChatMessage(message);
		}
	}

	@Override
	public ChannelBuffer encode(PlayerChatMessage message) {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		try {
			JSONObject json = new JSONObject();
			json.put("text", message.getMessage());
			ChannelBufferUtils.writeString(buffer,json.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
