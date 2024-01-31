import dayjs from 'dayjs/esm';
import { ComplaintStatus } from 'app/entities/enumerations/complaint-status.model';

export interface IComplaint {
  id?: number;
  complaintUUID?: string | null;
  complaintNo?: string | null;
  trademarkNo?: string | null;
  complaintDate?: dayjs.Dayjs | null;
  complaintOfficeReceivedDate?: dayjs.Dayjs | null;
  complaintPaymentReceipt?: string | null;
  complaintYear?: number | null;
  complainerPersonFullName?: string | null;
  complainerPersonJob?: string | null;
  complainerPersonNationality?: string | null;
  complainerPersonAddress?: string | null;
  complainerCompanyName?: string | null;
  complainerCompanyAddress?: string | null;
  complainerCompanyPurpose?: string | null;
  complainerCompanyHeadQuarterAddress?: string | null;
  complainerCompanyLibyaAddress?: string | null;
  pdfFileContentType?: string | null;
  pdfFile?: string | null;
  pdfFileUrl?: string | null;
  complaintStatus?: ComplaintStatus | null;
  complaintDetails?: string | null;
  notes?: string | null;
}

export class Complaint implements IComplaint {
  constructor(
    public id?: number,
    public complaintUUID?: string | null,
    public complaintNo?: string | null,
    public trademarkNo?: string | null,
    public complaintDate?: dayjs.Dayjs | null,
    public complaintOfficeReceivedDate?: dayjs.Dayjs | null,
    public complaintPaymentReceipt?: string | null,
    public complaintYear?: number | null,
    public complainerPersonFullName?: string | null,
    public complainerPersonJob?: string | null,
    public complainerPersonNationality?: string | null,
    public complainerPersonAddress?: string | null,
    public complainerCompanyName?: string | null,
    public complainerCompanyAddress?: string | null,
    public complainerCompanyPurpose?: string | null,
    public complainerCompanyHeadQuarterAddress?: string | null,
    public complainerCompanyLibyaAddress?: string | null,
    public pdfFileContentType?: string | null,
    public pdfFile?: string | null,
    public pdfFileUrl?: string | null,
    public complaintStatus?: ComplaintStatus | null,
    public complaintDetails?: string | null,
    public notes?: string | null
  ) {}
}

export function getComplaintIdentifier(complaint: IComplaint): number | undefined {
  return complaint.id;
}
