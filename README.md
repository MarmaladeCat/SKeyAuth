# 模拟 S/Key 动态口令验证(系统安全实验)
基于jdk1.8 

## 大致思路：
 服务器启动，4999端口，给用户初始化
 客户端启动，连接初始化服务器，初始化第一个密钥
初始化服务器关闭

验证服务器启动:5001
客户端启动，连接，带着密钥认证
	认证通过则返回成功更新为第二个密钥，验证不通过则返回失败
	......
下一次客户端需要带着新的密钥验证。


###初始化

用户产生一个秘密的口令字：SecretPASS
用户自己随机生成一个种子：SEED 
预处理：MD5(SecretPASS |SEED) ，生成初始化S。 
###生成口令序列

对S 做N 次S/KEY 安全散列，得到第1 个口令；
对S 做N-1 次S/KEY 安全散列，得到第2 个口令；
……
对S 做1 次S/KEY 安全散列，得第N 个口令
###口令使用

第1 个口令发送给服务器端保存
客户端顺序使用第2-N 个口令
###口令的验证

服务器端将收到的一次性口令传给验证函数
进行一次MD5运算。若与上一次保存的口令匹配，则认证通过并将收到的口令保存下次验证使用。	

###Client/Client.java：
ClientInitial：客户端初始化，根据用户名，密码产生客户数据结构，并初始化第一个动态口令发送给服务器端。
ClientAuth:客户端认证方法，与服务器端连接并以此试验第2-20个动态口令
###Server/Server.java：
ServerInitial：服务器端初始化，接收客户端连接并初始化客户数据结构，记录第一个动态口令。
ServerAuth：认证服务器，接收客户端连接，接收客户端认证请求并返回认证结果，记录客户认证操作。
###ClientInfo/ClientInfo.java
ClientInfo：ClientInfo数据结构，服务器端使用的客户数据结构，包含ClientInfo初始化方法，Authentication身份认证方法。
在Authentication中加入登录日志记录，采Apache的log4j模块。
###ClientInfo/OneClient.java
OneClient：OneClient数据结构，为客户端的客户数据结构，包含PassInitial初始化用户口令，GetPassN获取第N个动态口令等方法	


> 遇到的坑：
>
>1.Java中byte[]比较----字节数组比较
>方法一：
>使用==比较的是两个字节数组是否为同一个字节数组，此处不是比较两个字节数组的内容是否相同。
>方法二：
>使用equals方法比较，该方法也是比较是两个字节数组是否为同一个字节数组，即equals也不是比较内容的。
>方法三：
>如果是比较字节数组内容是否相等，使用Arrays.equals(a,b)方法比较，返回值为true或false。
>参考：https://blog.csdn.net/wangshuang1631/article/details/52791635
>
>2.Java中
>对于普通byte与String的转化，用iso-8859-1可逆
>new String(bs, "iso-8859-1")
>str.getBytes("iso-8859-1")
>
>3.Java中
>String+bytes+String字符串连接，直接的byte[]不是bytes的内容，需要自行转换到String再连接

