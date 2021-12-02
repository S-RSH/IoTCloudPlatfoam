import boto3

# Let's use Amazon S3
s3 = boto3.client('s3')

# Upload a new file
s3.upload_file(
    '1637850656.jpg', 'lambda-function-bucket-ap-northeast-2-1635049288998', 'picture/test2.jpg',
    ExtraArgs={'ACL': 'public-read'}
)
