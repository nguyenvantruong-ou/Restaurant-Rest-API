import { format } from 'date-fns';

const FormatDate = {
  formatFullDate(value) {
    return format(new Date(value), 'yyyy-MM-dd');
  },
};

export default FormatDate;
