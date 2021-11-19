package com.tolo.springaws.bucket;

public enum BucketName {
    PROFILE("spring-aws-1711");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
