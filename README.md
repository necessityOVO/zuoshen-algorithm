# 左神算法班笔记

> 可借鉴的笔记及源码解析链接：https://github.com/renyujie518/StrcuctandAlgorithm/tree/master/StructandAlgorithm/src

> 视频地址：https://www.bilibili.com/video/BV13g41157hK/

### 分支内容

- main - 自我练习实现
- 左神源码 - 左神算法课程的源码，目测版本不一样，可供参考
- other - 未知时间版本的源码，有缺漏

#### 待整理：

1. determinant_reasoning：当一个问题除了前几项是 base case 能直接已知结果外，后续的每一项都按照之前值的严格递推表达式来求出的问题，推理结果为`f(n, n-1,... n-m) = f(m,...2,1) * Math.pow(|{x x x} {x x x}{x x x}|, n - m)`
   典例：斐波那契数列的 O(logN)算法

2. 假设答案法：假设出最终答案，根据其位置推断出性质，再根据性质设计出简易的代码流程找出答案的位置

3. 打表法

#### TODO:

- [ ] 补充题目注释
- [ ] 总结技巧并添加示例题目链接
- [ ] 修改 main 分支仅保留自己的代码
- [ ] 重学行列式完成fibonacci文件夹


#### 思维：

1. 察觉已经计算过的部分并重用，或用缓存以重用
2. 要用废时间换空间，要么废空间换时间，此消彼长
3. 利用数学思维：分类讨论，数形结合

#### 优化：

1. 自身的数据状况
2. 所要求解的标准

#### 待强化：

- [ ] 小根堆，大根堆(例：skill-c18)
- [ ] 二分法(例：skill-c01)
- [ ] 行列式
- [ ] 编辑距离问题(https://leetcode-cn.com/problems/edit-distance/)
