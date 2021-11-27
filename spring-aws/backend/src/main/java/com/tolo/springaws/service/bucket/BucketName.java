package com.tolo.springaws.service.bucket;

public enum BucketName {
    FOOTBALL("spring-aws-1711");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
