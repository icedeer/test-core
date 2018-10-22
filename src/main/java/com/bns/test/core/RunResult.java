package com.bns.test.core;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RunResult {
	private final Status status;

	private final Instant startTime;
	private final Instant endTime;

	private final Map<String, Serializable> details;

	private RunResult(Builder builder) {
		this.status = builder.status;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Status getStatus() {
		return status;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public Map<String, Serializable> getDetails() {
		return this.details;
	}

	public Serializable getDetailOf(String name) {
		return this.details.get(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null && obj instanceof RunResult) {
			RunResult other = (RunResult) obj;
			return this.status.equals(other.status) && this.details.equals(other.details);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = this.status.hashCode();
		return hashCode + startTime.hashCode() + endTime.hashCode() + this.details.hashCode();
	}

	@Override
	public String toString() {
		return getStatus() + " " + getDetails();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder status(Status status) {
		return new Builder(status);
	}

	public static Builder success() {
		return status(Status.SUCCESS);
	}

	public static Builder failure() {
		return status(Status.FAILURE);
	}

	public static Builder skipped() {
		return status(Status.SKIPPED);
	}

	public static enum Status {
		UNKNOWN, SUCCESS, FAILURE, SKIPPED;
	}

	public static class Builder {
		private Status status;
		private Instant startTime;
		private Instant endTime;
		private Map<String, Serializable> details;

		public Builder() {
			this(Status.UNKNOWN);
		}

		public Builder(Status status) {
			this.startTime = Instant.now();
			this.status = status;
			this.details = new LinkedHashMap<String, Serializable>();
			this.endTime = Instant.now();
		}

		public Builder addDetail(String name, Serializable value) {
			details.put(name, value);
			return this;
		}

		public Builder startAt(Instant startTime) {
			assert startTime != null;
			assert !startTime.isAfter(endTime);
			this.startTime = startTime;
			return this;
		}

		public Builder endAt(Instant endTime) {
			assert endTime != null;
			assert !endTime.isAfter(Instant.now());
			assert !endTime.isBefore(startTime);
			this.endTime = endTime;
			return this;
		}

		public RunResult build() {
			return new RunResult(this);
		}
	}

}
