var url = '';

function projectStatus(index) {
  switch (index) {
    case 0:
      return '<span style="color: #1990FF;">筹集中</span>';
    case 1:
      return '<span style="color: #389E0D;">筹集结束</span>';
  }
}

function donateStatus(index) {
  switch (index) {
    case 0:
      return '<span style="color: #1990FF;">申请中</span>';
    case 1:
      return '<span style="color: #389E0D;">申请通过</span>';
    case 2:
      return '<span style="color: #F5222D;">申请失败</span>';
  }
}
