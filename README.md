# mybatis-gen-gradle
基于mybatis-generator的gradle插件
1.addComment 任务，为指定代码文件添加标识（即生成的java文件、xml文件除文件名、类名不变外，具体的内容加上包裹标签，方便后面替换）；
2.mybatisGenerate 任务，会先生成普通的代码文件，然后调用addComment任务添加包裹标签；
3.updateComment 任务，将新生成的代码文件去替换旧的代码文件，只替换包裹标签内部的内容；
