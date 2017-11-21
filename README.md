dagger2使用：
    参考资料：  http://www.jianshu.com/p/cd2c1c9f68d4
              http://blog.csdn.net/u012943767/article/details/51897247



### 依赖注入（Dependency Injection简称DI）
    就是目标类（目标类需要进行依赖初始化的类，下面都会用目标类一词来指代）中所依赖的其他的类的初始化过程，不是通过手动编码的方式创建，
    而是通过技术手段可以把其他的类的已经初始化好的实例自动注入到目标类中

1. 注解(Annotation)来标注目标类中所依赖的其他类，同样用注解来标注所依赖的其他类的构造函数
     class A{
            @Inject
            B b;
       }

       class B{
           @Inject
           B(){
           }
       }
    这样我们就可以让目标类中所依赖的其他类与其他类的构造函数之间有了一种无形的联系。但是要想使它们之间产生直接的关系，
        还得需要一个桥梁来把它们之间连接起来。那这个桥梁就是Component了。

2. Component也是一个注解类，一个类要想是Component，必须用Component注解来标注该类，并且该类是接口或抽象类
   Component在目标类中所依赖的其他类与其他类的构造函数之间可以起到一个桥梁的作用
   Component现在是一个注入器，就像注射器一样，Component会把目标类依赖的实例注入到目标类中，来初始化目标类中的依赖。

3. Module 把封装第三方类库的代码放入Module中
        @Module
        public class ModuleClass{
              //A是第三方类库中的一个类
              A provideA(){
                   return A();
              }
        }
        Module其实是一个简单工厂模式，Module里面的方法基本都是创建类实例的方法
4. 让Component与Module有联系
    Component是注入器，它一端连接目标类，另一端连接目标类依赖实例，它把目标类依赖实例注入到目标类中。上文中的Module是一个提供类实例的类，
    所以Module应该是属于Component的实例端的（连接各种目标类依赖实例的端），Component的新职责就是管理好Module，Component中的modules属性
    可以把Module加入Component，modules可以加入多个Module
5. Provides
    最终解决第三方类库依赖注入问题,把Module中的各种创建类的实例方法与目标类中的用Inject注解标注的依赖产生关联
    Module中的创建类实例方法用Provides进行标注，Component在搜索到目标类中用Inject注解标注的属性后，Component就会去Module中去查找用
    Provides标注的对应的创建类实例方法，这样就可以解决第三方类库用dagger2实现依赖注入了

### 流程
    用Inject注解标注目标类中其他类
    用Inject注解标注其他类的构造函数
    若其他类还依赖于其他的类，则重复进行上面2个步骤
    调用Component（注入器）的injectXXX（Object）方法开始注入（injectXXX方法名字是官方推荐的名字,以inject开始）

