## 1、登录 http://localhost:8080/login
@PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("login:{}",user);
        //调用Service层
        UserBasicInfo userBasicInfo = loginService.login(user);
        if(userBasicInfo!=null){
            return Result.success(userBasicInfo);
        }
        return Result.error("用户名或密码错误");
    }
## URL 

http://localhost:8080/login

## 返回的格式

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "id": 3,
        "username": "lisi",
        "name": "李四",
        "role": 1,
        "gender": "女",
        "age": 68,
        "phone": "13800138002",
        "email": "lisi@example.com",
        "healthRecordNumber": "HR20240002",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6bnVsbCwidXNlcm5hbWUiOiJsaXNpIiwiZXhwIjoxNzYxODcyOTEyfQ.uZ0OKGINbqU7H4hfFV4oy7a9TvmpPnG0g2LgJV4Y6eo"
    }
}
```

4、用户pojo表

```java
/**
 * 用户创建请求
 */
@Data
public class User {
    private Integer id; //ID,主键
    private String username;            // 用户名
    private String password;            // 密码
    private String name;                // 姓名
    private String gender;              // 性别
    private Integer age;                // 年龄
    private String phone;               // 手机号
    private String email;               // 邮箱
    private String healthRecordNumber;  // 健康档案号
    private Integer role;               // 角色（默认1，表示user）
    private String status;              // 状态（默认active）
}
```

# 2、统计 http://localhost:8080/admin/count

## 接收器

```java
@Slf4j
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    // 统计用户数 员工数 部门数量
    @PostMapping("/count")
    public Result count(){
        log.info("Admin:count");
        // 统计用户数
        return Result.success(adminService.countAll());

    }
}
```

##  URL
Post请求
http://localhost:8080/admin/count

## 返回的格式

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "userCount": 8, //总用户数
        "aiConsultCount": 0, //AI咨询次数
        "departmentCount": 5, //部门总数
        "employeeCount": 7 //员工总数
        //今日活跃用户 默认为0
        //总服务数 默认为6
    }
}
```
# 3、员工分页查询 http://localhost:8080/admin/emp/list
接口基础信息
请求路径：http://localhost:8080/admin/emp/list（本地环境，端口默认 8080，若修改过端口需对应调整）
请求方式：POST
请求格式：JSON（表单查询条件）
响应格式：JSON（Result 包装分页数据）

## 请求参数（JSON格式）
```json
{
  "page": 1,
  "pageSize": 10,
  "name": "王",
  "gender": 1,
  "position": "医师",
  "departmentName": "内科",
  "begin": "2025-10-01",
  "end": "2025-11-01"
}
```

## 查询条件对象
```java
/**
 * 员工查询条件
 */
@Data
public class EmpQueryDTO {
    private Integer page = 1; // 页码（默认1）
    private Integer pageSize = 10; // 每页条数（默认10）
    private String name; // 员工姓名（模糊查询）
    private Integer gender; // 性别（0 - 未知 / 1 - 男 / 2 - 女）
    private String position; // 职位（模糊查询）
    private String departmentName; // 部门名称（模糊查询）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime begin; // 创建时间开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime end; // 创建时间结束
}
```

## 接收器
@RestController
@Slf4j
@RequestMapping("/admin/emp")
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;
    
```java
/**
 * 多条件分页查询员工列表
 * @param queryDTO 查询条件
 * @return 分页结果
 */
@PostMapping("/list")  // 员工列表（表单查询）
public Result list(@RequestBody EmpQueryDTO queryDTO) {
    log.info("查询请求参数：{}", queryDTO);
    return Result.success(empService.findAll(
        queryDTO.getPage(),
        queryDTO.getPageSize(),
        queryDTO.getName(),
        queryDTO.getGender(),
        queryDTO.getPosition(),
        queryDTO.getDepartmentName(),
        queryDTO.getBegin(),
        queryDTO.getEnd()
    ));
}
```
}
##  URL 
GET类型 http://localhost:8080/admin/emp/list

## 返回的格式
```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "rows": [
            {
                "id": 1,
                "name": "张医生",
                "position": "医师",
                "departmentId": null,
                "departmentName": "体检科",
                "phone": "13811111111",
                "status": 1,   //'状态（0-离职/1-在职）';
                "createdAt": "2025-10-31T20:18:36",
                "updatedAt": "2025-10-31T20:18:36"
            },
            {
                "id": 2,
                "name": "李护士",
                "position": "护士",
                "departmentId": null,
                "departmentName": "社区医疗",
                "phone": "13811111112",
                "status": 1,   //'状态（0-离职/1-在职）';   
                "createdAt": "2025-10-31T20:18:36",
                "updatedAt": "2025-10-31T20:18:36"
            },
            {
                "id": 3,
                "name": "王医生",
                "position": "主任医师",
                "departmentId": null,
                "departmentName": "内科",
                "phone": "13811111113",
                "status": 1,   //'状态（0-离职/1-在职）';       
                "createdAt": "2025-10-31T20:18:36",
                "updatedAt": "2025-10-31T20:18:36"
            }
        ],
        "total": 7
    }
}
```



# 4、添加员工 http://localhost:8080/admin/emp/add

## 接收器
```java

/**
     * 添加员工
     * @param emp
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Emp emp){
        log.info("添加员工：{}", emp);
        empService.addEmp(emp);
        return Result.success();

    }
```

##  URL
POST http://localhost:8080/admin/emp/add
## 数据的格式
```xml
<!--     添加员工-->
    <insert id="addEmp" parameterType="com.thegentle.oldhealth.pojo.Emp.Emp">
    insert into employees(name, gender, position, department_id, phone, status, created_at, updated_at)
    values(#{name}, #{gender}, #{position}, #{departmentId}, #{phone}, #{status}, #{createdAt}, #{updatedAt})
    </insert>
```

## 发送示例

```json
{
  "name": "赵护士",
  "gender": 2,    //0未知1男2女
  "position": "护士长",
  "departmentId": 3,
  "phone": "13899999999",
  "status": 1,    //0-离职/1-在职
  "createdAt": "2025-11-04T10:30:00", // 空格改为 T
  "updatedAt": "2025-11-04T10:30:00"  // 空格改为 T
}
```



# 5、删除员工 http://localhost:8080/admin/emp/delete

## 接收器
```java
    /**
     * 删除员工
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }
```

##  URL
POST  http://localhost:8080/admin/emp/delete
## 参数格式
```java
List<Integer> ids  //要求1、前端通过复选框 实现批量删除功能 2、删除按钮也可以通过单个id删除对应单个对象 
```

# 6、修改员工 http://localhost:8080/admin/emp/update

## 接收器

```java
/**
 * 修改员工
 * @param emp
 * @return
 */
@PostMapping("/update")
public Result update(@RequestBody Emp emp){
    log.info("修改员工：{}", emp);
    empService.updateEmp(emp);
    return Result.success();
}
```

##  URL

POST ：http://localhost:8080/admin/emp/update

## 传递的格式

```java
Emp emp   //emp所需要修改的参数值
```

## Mapper 数据库映射操作

```XML
<!--     修改员工-->
<update id="updateEmp" parameterType="com.thegentle.oldhealth.pojo.Emp.Emp">
    update employees
    <set>
        <if test="name != null">
            name = #{name},
        </if>
        <if test="gender != null">
            gender = #{gender},
        </if>
        <if test="position != null">
            position = #{position},
        </if>
        <if test="departmentId != null">
            department_id = #{departmentId},
        </if>
        <if test="phone != null">
            phone = #{phone},
        </if>
        <if test="status != null">
            status = #{status},
        </if>
    </set>
</update>
```

## 成功返回格式（示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```





# 7、回显员工信息 http://localhost:8080/admin/emp/findEmpbyId/{id}

## 接收器

```java
/**
 * 根据id查询员工
 * @param id
 * @return
 */
@GetMapping("/findEmpbyId/{id}")
public Result findEmpbyId(@PathVariable Integer id){
    log.info("根据id回显员工：{}", id);
    return Result.success(empService.findEmpbyId(id));
}
```

## URL

GET ：http://localhost:8080/admin/emp/findEmpbyId/{id}

## 传递的格式

```java
Integer id   //员工的唯一ID
```

## Mapper映射

```xml
<!--     查询员工-->
    <select id="findEmpbyId" parameterType="java.lang.Integer" resultType="com.thegentle.oldhealth.pojo.Emp.Emp">
        select
        e.id,
        e.name,
        e.gender,
        e.position,
        d.department_name as department_name,
        e.department_id,
        e.phone,
        e.status,
        e.created_at,
        e.updated_at
        from employees e
        left join departments d on e.department_id = d.id
        where e.id = #{id}
    </select>
```



## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "id": 2,
        "name": "李护士",
        "gender": "0",
        "position": "护士",
        "departmentId": null,
        "departmentName": "社区医疗",
        "phone": "13811111112",
        "status": "1",
        "createdAt": "2025-10-31T20:18:36",
        "updatedAt": "2025-11-04T00:55:58"
    }
}
```

## 功能

获取回显的数据显示在编辑用户信息的对话框中



# 8、动态员工 http://localhost:8080/admin/emp/dynamic

## 接收器

```java
//    动态部门经理
    @GetMapping("/dynamic")
    public Result dynamic(@RequestParam String name){
        return Result.success(empService.dynamic(name));
    }
```

## URL

GET ： http://localhost:8080/admin/emp/dynamic

## 传递的格式

```java
Map<String,Integer>  // name:id
```

## Mapper映射

```xml
<!--    // 动态查询员工-->
    <select id="dynamic" resultType="com.thegentle.oldhealth.pojo.Emp.Emp">
        select id, name
        from employees
        <where>
            <if test="name != null">
                name like concat('%', #{name}, '%')
            </if>
        </where>
    </select>
```



## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "王医生": 3
    }
}
```

## 功能

编辑部门经理的下拉选择框中的内容要根据改api实现动态变化

# 9、动态部门 http://localhost:8080/admin/empt/findDeptIdAndName

## 接收器

```java
    // 只查询部门id和部门名称
    @GetMapping("/findDeptIdAndName")
    public Result findDeptIdAndName(){
        return Result.success(deptService.findDeptIdAndName());
    }
```

## URL

GET ： http://localhost:8080/admin/empt/findDeptIdAndName

## 传递的格式

```java
Map<Integer,String>  // id:deptname
```

## Mapper映射

```xml
    <!-- 只查询部门ID和部门名称（无需关联员工表，保持优化） -->
    <select id="findDeptIdAndName" resultType="com.thegentle.oldhealth.pojo.Department.Dept">
        SELECT
            d.id,
            d.department_name AS departmentName
        FROM departments d;
    </select>
```



## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "1": "体检科",
        "2": "社区医疗",
        "3": "内科",
        "4": "内分泌科",
        "5": "心内科"
    }
}
```

## 功能

编辑员工信息的下拉选择框中的内容要根据改api实现动态变化



# 10、部门信息 http://localhost:8080/admin/dept/list

## 接收器

```java
    // 查询部门列表
    @GetMapping("/list")
    public Result list(){
        //1、查询部门列表
        List<Dept> list=deptService.list();
        return Result.success(list);
    }
```

## URL

GET ：  http://localhost:8080/admin/dept/list

## 传递的格式

```java
List<Dept> //  部门信息
```



## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": [
        {
            "id": 1,
            "departmentName": "体检科",
            "description": "负责健康体检和健康评估服务",
            "managerId": 1,
            "managerName": "张医生",
            "employeeCount": 1,   //部门人数统计 只包含在职的
            "createdAt": "2025-10-31T12:18:36.000+00:00",
            "updatedAt": "2025-10-31T12:18:36.000+00:00"
        },
        {
            "id": 2,
            "departmentName": "社区医疗",
            "description": "提供社区医疗服务和上门问诊",
            "managerId": 7,
            "managerName": "陈护士",
            "employeeCount": 3,
            "createdAt": "2025-10-31T12:18:36.000+00:00",
            "updatedAt": "2025-10-31T12:18:36.000+00:00"
        },
        {
            "id": 3,
            "departmentName": "内科",
            "description": "提供内科疾病诊疗服务",
            "managerId": 3,
            "managerName": "王医生",
            "employeeCount": 1,
            "createdAt": "2025-10-31T12:18:36.000+00:00",
            "updatedAt": "2025-10-31T12:18:36.000+00:00"
        },
        {
            "id": 4,
            "departmentName": "内分泌科",
            "description": "专注于内分泌疾病管理",
            "managerId": 4,
            "managerName": "赵医生",
            "employeeCount": 1,
            "createdAt": "2025-10-31T12:18:36.000+00:00",
            "updatedAt": "2025-10-31T12:18:36.000+00:00"
        },
        {
            "id": 5,
            "departmentName": "心内科",
            "description": "专注于心血管疾病诊疗",
            "managerId": 6,
            "managerName": "刘医生",
            "employeeCount": 1,
            "createdAt": "2025-10-31T12:18:36.000+00:00",
            "updatedAt": "2025-10-31T12:18:36.000+00:00"
        }
    ]
}
```

## 功能

获取部门所有的信息用于前端管理和显示





# 11、添加部门 http://localhost:8080/admin/dept/add

## 接收器

```java
    // 添加部门
    @PostMapping("/add")
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}", dept);
        return deptService.add(dept)==null?Result.success():Result.error("部门名称已经存在");
    }
```

## URL

POST ：  http://localhost:8080/admin/dept/add

## 传递的格式

```java
Dept dept  // 部门信息
```

## Mapper映射

```xml
<!-- 添加部门（增加SQL兜底：若传入0则转为NULL，避免业务层漏处理） -->
<insert id="add">
    INSERT INTO departments(department_name, description, manager_id, created_at, updated_at)
    VALUES (
               #{departmentName},
               #{description},
               -- 兜底：若业务层未将0转为NULL，SQL层面强制转换
               CASE WHEN #{managerId} = 0 THEN NULL ELSE #{managerId} END,
               #{createdAt},
               #{updatedAt}
           );
</insert>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```



## 功能

添加部门信息其中包含了判断该部门是否已经存在





# 12、修改部门 http://localhost:8080/admin/dept/update

## 接收器

```java
    // 修改部门
    @PostMapping("/update")
    public Result update(@RequestBody Dept dept){

        log.info("修改部门：{}", dept);
        return deptService.update(dept)==null?Result.success():Result.error("部门名称已经存在");
    }
```

## URL

POST ：  http://localhost:8080/admin/dept/update

## 传递的格式

```java
Dept dept  // 部门信息
```

## Mapper映射

```xml
<!-- 修改部门（逻辑保持不变，允许传NULL） -->
<update id="update">
    UPDATE departments
    <set>
        <if test="departmentName != null and departmentName != ''">
            department_name = #{departmentName},
        </if>
        <if test="description != null and description != ''">
            description = #{description},
        </if>
        <if test="managerId != null"> <!-- 传NULL表示设为无主理人，传具体ID表示指定主理人 -->
            manager_id = #{managerId},
        </if>
        <if test="updatedAt != null">
            updated_at = #{updatedAt},
        </if>
    </set>
    WHERE id = #{id};
</update>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```

## 功能

修改部门信息其中包含了判断修改过后该部门是否已经存在



# 13、回显部门 http://localhost:8080/admin/dept//findById/{id}

## 接收器

```java
    //回显部门
    @GetMapping("/findById")
    public Result findById(@PathVariable Integer id){
        log.info("回显部门：{}", id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }
```

## URL

GET ： http://localhost:8080/admin/dept/findById/{id}

## 传递的格式

```java
Integer id  // =需要回显的id
```

## Mapper映射

```xml
<!-- 根据id查询部门（逻辑保持不变，与list方法统一） -->
<select id="findById" resultType="com.thegentle.oldhealth.pojo.Department.Dept">
    SELECT
        d.id,
        d.department_name AS departmentName,
        d.description,
        d.manager_id AS managerId,
        CASE
            WHEN d.manager_id IS NULL THEN '无'
            ELSE COALESCE(e.name, '无')
            END AS managerName,
        CASE
            WHEN d.manager_id IS NULL THEN 0
            ELSE (
                SELECT COUNT(*)
                FROM employees emp
                WHERE emp.department_id = d.id
                  AND emp.status = 1
            )
            END AS employeeCount,
        d.created_at,
        d.updated_at
    FROM departments d
             LEFT JOIN employees e
                       ON d.manager_id = e.id
                           AND d.manager_id IS NOT NULL
    WHERE d.id = #{id};
</select>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "id": 1,
        "departmentName": "体检科",
        "description": "负责健康体检和健康评估服务",
        "managerId": 1,
        "managerName": "张医生",
        "employeeCount": 1,
        "createdAt": "2025-10-31T20:18:36",
        "updatedAt": "2025-10-31T20:18:36"
    }
}
```

## 功能

在修改部门信息的时候，在对话框中 回显该部门原来的数据信息，方便修改和查看。 



# 14、删除部门 http://localhost:8080/admin/dept/delete

## 接收器

```java
 // 删除部门
    @DeleteMapping("/delete")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除部门：{}", ids);
        deptService.delete(ids);
        return Result.success();
    }
```

## URL

DELETE：http://localhost:8080/admin/dept/delete

## 传递的格式

```java
List<Integer> ids  //要删除的 ids
```

## Mapper映射

```xml
<!-- 删除部门（逻辑无问题，保持不变） -->
<delete id="delete">
    DELETE FROM departments
    WHERE id IN
    <foreach collection="list" item="id" separator="," open="(" close=")">
        #{id}
    </foreach>
</delete>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```

## 功能

删除对应部门信息



# 15、添加用户 http://localhost:8080/user/addUser

## 接收器

```java
    //添加 用户
    @PostMapping("/user/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("addUser:{}",user);
        return userService.addUser(user)==1?Result.success():Result.error("添加失败");
    }
```

## URL

POST ： http://localhost:8080/user/addUser

## 传递的格式

```java
User user
```

## Mapper映射

```xml
<!--    //添加用户基本信息-->
<insert id="addUser">
    insert into users(username,password,name,gender,age,phone,email,health_record_number,role,status,created_at,updated_at,register_time)
    values(#{username},#{password},#{name},#{gender},#{age},#{phone},#{email},#{healthRecordNumber},#{role},#{status},#{createdAt},#{updatedAt},#{registerTime})
</insert>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```

## 功能

添加用户其中用户类型role以及用户的状态status 用下拉选择框来确定

role INT NOT NULL DEFAULT 1 COMMENT '角色（0表示admin，1表示user）',

status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '状态（active/inactive）',



# 16、分页获取用户 http://localhost:8080/user/getUserList

## 接收器

```java
    //分页获取用户列表
    @GetMapping("/user/getUserList")
    public Result getUserList(String username,
                              String phone,
                              String email,
                              Integer role,
                              String status,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam (defaultValue = "10") Integer pageSize) {
        log.info("getUserList:{},{},{},{},{}, {},{},{},{},pagesize:{}", page, pageSize, username, phone, email,role,status,begin,end,pageSize);
        return Result.success(userService.getUserList(username,phone,email,role,status,begin,end,page,pageSize));
    }
```

## URL

GET ：http://localhost:8080/user/getUserList

## 传递的格式

```java
String username,
String phone,
String email,
Integer role,
String status,
@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
@RequestParam(defaultValue = "1") Integer page,
@RequestParam (defaultValue = "10") Integer pageSize
```

## Mapper映射

```xml
<!--    //获取用户列表-->
    <select id="getUserList" resultType="com.thegentle.oldhealth.pojo.User.User">
        select id,username,name,role,gender,age,phone,email,health_record_number,status from users
        <where>
            <if test="username!=null and username!=''">
                username like '%${username}%'
            </if>
            <if test="phone!=null and phone!=''">
                and phone like '%${phone}%'
            </if>
            <if test="email!=null and email!=''">
                and email like '%${email}%'
            </if>
            <if test="role!=null and role!=''">
                and role=#{role}
            </if>
            <if test="status!=null and status!=''">
                and status=#{status}
            </if>
            <if test="begin!=null and begin!=''">
                and create_time&gt;=#{begin}
            </if>
            <if test="end!=null and end!=''">
                and create_time&lt;=#{end}
            </if>
        </where>
    </select>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "rows": [
            {
                "id": 1,
                "username": "admin",
                "password": null,
                "name": "系统管理员",
                "gender": "男",
                "age": 35,
                "phone": "13800138000",
                "email": "admin@healthplatform.com",
                "healthRecordNumber": "ADMIN0001",
                "role": 0,
                "status": "active",
                "createdAt": null,
                "updatedAt": null,
                "registerTime": null,
                "lastLoginTime": null
            },
            {
                "id": 2,
                "username": "zhangsan",
                "password": null,
                "name": "张三",
                "gender": "男",
                "age": 65,
                "phone": "13800138001",
                "email": "zhangsan@example.com",
                "healthRecordNumber": "HR20240001",
                "role": 1,
                "status": "active",
                "createdAt": null,
                "updatedAt": null,
                "registerTime": null,
                "lastLoginTime": null
            }
        ],
        "total": 17
    }
}
```

## 功能

分页查询查询用户列表


# 17、修改用户 http://localhost:8080/user/updateUser

## 接收器

```java
    /**
     * 修改用户
     * @param user 包含更新信息的用户对象
     * @return 更新结果
     */
    @PostMapping("/user/updateUser")
    public Result updateUser(@RequestBody User user) {
        log.info("修改用户: {}", user);
        userService.updateUser(user);
        return Result.success();
    }
```

## URL

POST ：http://localhost:8080/user/updateUser

## 传递的格式

```json
{
    "id": 2,
    "username": "zhangsan",
    "name": "张三",
    "gender": "男",
    "age": 65,
    "phone": "13800138001",
    "email": "zhangsan@example.com",
    "healthRecordNumber": "HR20240001",
    "role": 1,
    "status": "active"
}
```

## Mapper映射

```xml
<!-- 修改用户 -->
<update id="updateUser">
    update users 
    set 
        name=#{name},
        gender=#{gender},
        age=#{age},
        phone=#{phone},
        email=#{email},
        health_record_number=#{healthRecordNumber},
        role=#{role},
        status=#{status},
        updated_at=#{updatedAt} 
    where id=#{id}
</update>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```

## 功能
更新指定用户的基本信息

# 18、删除用户 http://localhost:8080/user/deleteUser/{ids}

## 接收器

```java
    /**
     * 删除用户（批量）
     * @param ids 用户ID列表
     * @return 删除结果
     */
    @PostMapping("/user/deleteUser/{ids}")
    public Result deleteUser(@PathVariable List<Integer> ids) {
        log.info("删除用户: 用户ID列表 = {}", ids);
        userService.deleteUser(ids);
        return Result.success();
    }
```

## URL

POST ：http://localhost:8080/user/deleteUser/1,2,3

## 传递的格式
路径参数：ids - 用户ID列表，多个ID用逗号分隔

## Mapper映射

```xml
<!-- 删除用户 -->
<delete id="deleteUser">
    delete from users where id in (#{ids})
</delete>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": null
}
```

## 功能
批量删除指定ID的用户

# 19、用户登录 http://localhost:8080/login

## 接收器

```java
    /**
     * 用户登录接口
     * @param user 包含用户名和密码的用户对象
     * @return 登录结果，成功返回用户基本信息和token，失败返回错误信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录: {}", user);
        //调用Service层进行登录验证
        UserBasicInfo userBasicInfo = userService.login(user);
        if (userBasicInfo != null) {
            return Result.success(userBasicInfo);
        }
        return Result.error("用户名或密码错误");
    }
```

## URL

POST ：http://localhost:8080/login

## 传递的格式

```json
{
    "username": "admin",
    "password": "123456"
}
```

## Mapper映射

```xml
<!-- 用户登录 -->
<select id="SelectByUsernameAndPassword" resultType="com.thegentle.oldhealth.pojo.User.User">
    select id,username,name,role,gender,age,phone,email,health_record_number 
    from users 
    where username = #{username} and password = #{password}
</select>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "id": 1,
        "username": "admin",
        "name": "系统管理员",
        "role": 0,
        "gender": "男",
        "age": 35,
        "phone": "13800138000",
        "email": "admin@healthplatform.com",
        "healthRecordNumber": "ADMIN0001",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
}
```

## 功能
用户登录验证，并返回用户基本信息和身份认证令牌

# 20、用户注册 http://localhost:8080/register

## 接收器

```java
    /**
     * 用户注册接口
     * @param user 包含用户注册信息的对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("用户注册: {}", user);
        //调用Service层进行注册处理
        return userService.register(user) == 1
                ? Result.success("注册成功")
                : Result.error("用户已存在");
    }
```

## URL

POST ：http://localhost:8080/register

## 传递的格式

```json
{
    "username": "newuser",
    "password": "123456",
    "name": "新用户",
    "gender": "女",
    "age": 60,
    "phone": "13800138002",
    "email": "newuser@example.com"
}
```

## Mapper映射

```xml
<!-- 用户注册 -->
<insert id="register">
    insert into users(username, password, name, gender, age, phone, email, health_record_number, role, status, created_at, updated_at, register_time) 
    values(#{username}, #{password}, #{name}, #{gender}, #{age}, #{phone}, #{email}, #{healthRecordNumber}, #{role}, #{status}, #{createdAt}, #{updatedAt}, #{registerTime})
</insert>

<!-- 根据用户名查询用户 -->
<select id="selectByUsername" resultType="com.thegentle.oldhealth.pojo.User.User">
    SELECT id FROM users WHERE username = #{username}
</select>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "注册成功",
    "data": null
}
```

## 功能
用户注册，创建新用户账号

# 21、获取用户基本信息 http://localhost:8080/user/getUserBasicInfo/{id}

## 接收器

```java
    /**
     * 获取用户基本信息
     * @param id 用户ID
     * @return 用户基本信息
     */
    @GetMapping("/user/getUserBasicInfo/{id}")
    public Result getUserBasicInfo(@PathVariable Integer id) {
        log.info("获取用户基本信息: 用户ID = {}", id);
        User user = new User();
        user.setId(id);
        return Result.success(userService.getUserBasicInfo(user));
    }
```

## URL

GET ：http://localhost:8080/user/getUserBasicInfo/1

## 传递的格式
路径参数：id - 用户ID

## Mapper映射

```xml
<!-- 获取用户基本信息 -->
<select id="getUserBasicInfo" resultType="com.thegentle.oldhealth.pojo.User.User">
    select id,username,name,role,gender,age,phone,email,health_record_number 
    from users 
    where id = #{id}
</select>
```

## 成功返回的格式 （示例）

```json
{
    "code": 1,
    "msg": "操作成功",
    "data": {
        "id": 1,
        "username": "admin",
        "name": "系统管理员",
        "role": 0,
        "gender": "男",
        "age": 35,
        "phone": "13800138000",
        "email": "admin@healthplatform.com",
        "healthRecordNumber": "ADMIN0001"
    }
}
```

## 功能
根据用户ID获取用户基本信息



# 17、修改用户 http://localhost:8080/user/updateUser

## 接收器

```java
    // 只查询部门id和部门名称
    @GetMapping("/findDeptIdAndName")
    public Result findDeptIdAndName(){
        return Result.success(deptService.findDeptIdAndName());
    }
```

## URL

POST ：http://localhost:8080/user/updateUser

## 传递的格式

```java

```

## Mapper映射



## 成功返回的格式 （示例）

```json

```

## 功能









